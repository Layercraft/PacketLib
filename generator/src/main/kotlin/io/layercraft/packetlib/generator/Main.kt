package io.layercraft.packetlib.generator

import io.layercraft.packetlib.generator.codegen.CodecGenerator
import io.layercraft.packetlib.generator.codegen.GeneratedPacket
import io.layercraft.packetlib.generator.codegen.PacketFileGenerator
import io.layercraft.packetlib.generator.codegen.Runtime
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.deleteRecursively
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name

@OptIn(ExperimentalPathApi::class)
fun main(args: Array<String>) {
    require(args.size >= 2) { "Usage: generator <output source dir> <cache dir> [versions...]" }
    val outputDir = Path.of(args[0])
    val cacheDir = Path.of(args[1])
    val versionFilter = args.drop(2).filter { it.isNotBlank() }

    val data = MinecraftData(cacheDir)
    val plan = VersionPlanner.plan(data, versionFilter)

    println("Generating ${plan.versions.size} versions (${plan.canonicalVersions.size} distinct protocols)")

    // Clean previously generated sources (only the per-version packages, keeping Packet.kt etc.).
    val packetsDir = outputDir.resolve(Runtime.PACKETS_PACKAGE.replace('.', '/'))
    if (Files.isDirectory(packetsDir)) {
        packetsDir.listDirectoryEntries("v*").filter { Files.isDirectory(it) }.forEach { it.deleteRecursively() }
    }
    val codecDir = outputDir.resolve(Runtime.CODEC_PACKAGE.replace('.', '/'))
    if (Files.isDirectory(codecDir)) {
        codecDir.listDirectoryEntries("*.kt")
            .filter { it.name == "MinecraftCodecs.kt" || it.name == "ProtocolVersion.kt" || it.name.startsWith("CodecV") }
            .forEach { Files.delete(it) }
    }

    var typedCount = 0
    var fallbackCount = 0
    val fallbackReasons = mutableMapOf<String, Int>()

    for (version in plan.canonicalVersions) {
        val protocol = data.protocol(version.folder)
        val fileGenerator = PacketFileGenerator(versionPackage(version.version), version.protocolNumber, version.folder)

        val generated = mutableListOf<GeneratedPacket>()
        for (extracted in ProtocolExtractor.extract(protocol)) {
            for (definition in extracted.packets) {
                val (file, packet) = fileGenerator.generate(definition, extracted.resolver)
                file.writeTo(outputDir)
                generated += packet

                if (packet.fallbackReason == null) {
                    typedCount++
                } else {
                    fallbackCount++
                    fallbackReasons.merge(packet.fallbackReason, 1, Int::plus)
                }
            }
        }

        CodecGenerator.codecFile(version, generated).writeTo(outputDir)

        val fallbacks = generated.count { it.fallbackReason != null }
        println("${version.version}: ${generated.size} packets (${generated.size - fallbacks} typed, $fallbacks raw fallback)")
    }

    CodecGenerator.protocolVersionFile(plan).writeTo(outputDir)
    CodecGenerator.minecraftCodecsFile(plan).writeTo(outputDir)

    println()
    println("Done: $typedCount typed packets, $fallbackCount raw fallback packets")
    println("Fallback reasons:")
    fallbackReasons.entries.sortedByDescending { it.value }.forEach { (reason, count) ->
        println("  $count x $reason")
    }
}
