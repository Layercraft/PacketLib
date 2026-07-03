package io.layercraft.packetlib.generator.codegen

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MAP
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import io.layercraft.packetlib.generator.GenerationPlan
import io.layercraft.packetlib.generator.PlannedVersion

/** Generates ProtocolVersion, the per-version codec builders and the MinecraftCodecs registry. */
object CodecGenerator {

    fun protocolVersionFile(plan: GenerationPlan): FileSpec {
        val enumBuilder = TypeSpec.enumBuilder(Runtime.PROTOCOL_VERSION)
            .primaryConstructor(
                FunSpec.constructorBuilder()
                    .addParameter("protocolNumber", Int::class)
                    .addParameter("version", String::class)
                    .build(),
            )
            .addProperty(PropertySpec.builder("protocolNumber", Int::class).initializer("protocolNumber").build())
            .addProperty(PropertySpec.builder("version", String::class).initializer("version").build())
            .addKdoc("All Minecraft release versions covered by the generated packets, in release order.\n")

        for (version in plan.versions) {
            enumBuilder.addEnumConstant(
                version.enumName,
                TypeSpec.anonymousClassBuilder()
                    .addSuperclassConstructorParameter("%L, %S", version.protocolNumber, version.version)
                    .build(),
            )
        }

        enumBuilder.addType(
            TypeSpec.companionObjectBuilder()
                .addFunction(
                    FunSpec.builder("fromProtocolNumber")
                        .addParameter("protocolNumber", Int::class)
                        .returns(Runtime.PROTOCOL_VERSION.copy(nullable = true))
                        .addCode("return entries.lastOrNull·{ it.protocolNumber == protocolNumber }\n")
                        .build(),
                )
                .addFunction(
                    FunSpec.builder("fromVersion")
                        .addParameter("version", String::class)
                        .returns(Runtime.PROTOCOL_VERSION.copy(nullable = true))
                        .addCode("return entries.firstOrNull·{ it.version == version }\n")
                        .build(),
                )
                .build(),
        )

        return generatedFile(Runtime.CODEC_PACKAGE, "ProtocolVersion")
            .addType(enumBuilder.build())
            .build()
    }

    fun codecFile(version: PlannedVersion, packets: List<GeneratedPacket>): FileSpec {
        val body = CodeBlock.builder()
        body.add("return %M(%T.%N) {\n", Runtime.CODEC_FUN, Runtime.PROTOCOL_VERSION, version.enumName)
        body.indent()

        val byState = packets.groupBy { it.definition.state }
        for ((state, statePackets) in byState) {
            body.add("packets(%T.%N) {\n", Runtime.PACKET_STATE, state.uppercase())
            body.indent()
            for (packet in statePackets.sortedWith(compareBy({ it.definition.direction }, { it.definition.id }))) {
                val registrar = if (packet.definition.direction == Direction.CLIENTBOUND) "clientBound" else "serverBound"
                body.add("%N(0x%L, %T::class, %T)\n", registrar, "%02x".format(packet.definition.id), packet.className, packet.className)
            }
            body.unindent()
            body.add("}\n")
        }

        body.unindent()
        body.add("}\n")

        return generatedFile(Runtime.CODEC_PACKAGE, "Codec${version.enumName}")
            .addFunction(
                FunSpec.builder("create${version.enumName}")
                    .addModifiers(KModifier.INTERNAL)
                    .returns(Runtime.CODEC)
                    .addKdoc("Codec for Minecraft %L (protocol %L).\n", version.version, version.protocolNumber)
                    .addCode(body.build())
                    .build(),
            )
            .build()
    }

    fun minecraftCodecsFile(plan: GenerationPlan): FileSpec {
        val objectBuilder = TypeSpec.objectBuilder(ClassName(Runtime.CODEC_PACKAGE, "MinecraftCodecs"))
            .addKdoc("All generated packet codecs, one per Minecraft release version.\n")

        for (version in plan.versions) {
            val initializer = if (version.canonical) {
                CodeBlock.of("lazy·{ create%N() }", version.enumName)
            } else {
                val canonical = plan.canonicalOf(version)
                CodeBlock.of(
                    "lazy·{ %M(%T.%N, %N) }",
                    Runtime.CODEC_COPY_FUN, Runtime.PROTOCOL_VERSION, version.enumName, canonical.enumName,
                )
            }
            objectBuilder.addProperty(
                PropertySpec.builder(version.enumName, Runtime.CODEC)
                    .delegate(initializer)
                    .addKdoc("Minecraft %L (protocol %L)\n", version.version, version.protocolNumber)
                    .build(),
            )
        }

        val allInitializer = CodeBlock.builder()
        allInitializer.add("lazy·{\n")
        allInitializer.indent()
        allInitializer.add("mapOf(\n")
        allInitializer.indent()
        for (version in plan.versions) {
            allInitializer.add("%T.%N·to·%N,\n", Runtime.PROTOCOL_VERSION, version.enumName, version.enumName)
        }
        allInitializer.unindent()
        allInitializer.add(")\n")
        allInitializer.unindent()
        allInitializer.add("}")

        objectBuilder.addProperty(
            PropertySpec.builder("ALL", MAP.parameterizedBy(Runtime.PROTOCOL_VERSION, Runtime.CODEC))
                .delegate(allInitializer.build())
                .addKdoc("Every codec by protocol version.\n")
                .build(),
        )

        objectBuilder.addFunction(
            FunSpec.builder("fromProtocolVersion")
                .addParameter("protocolVersion", Runtime.PROTOCOL_VERSION)
                .returns(Runtime.CODEC)
                .addCode("return ALL.getValue(protocolVersion)\n")
                .build(),
        )

        return generatedFile(Runtime.CODEC_PACKAGE, "MinecraftCodecs")
            .addType(objectBuilder.build())
            .build()
    }

    private fun generatedFile(packageName: String, name: String): FileSpec.Builder =
        FileSpec.builder(packageName, name)
            .addFileComment("Generated by the packetlib generator from minecraft-data. Do not edit manually.")
            .indent("    ")
}
