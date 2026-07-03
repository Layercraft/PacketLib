package io.layercraft.packetlib.generator

import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * One Minecraft release version that will be part of the generated library.
 *
 * Multiple versions can share the same protocol data folder; the [canonical] version of a folder
 * gets its own generated packet package, the others reuse it via `codecCopy`.
 */
data class PlannedVersion(
    val version: String,
    val protocolNumber: Int,
    val folder: String,
    val canonical: Boolean,
) {
    val enumName: String = versionEnumName(version)
}

data class GenerationPlan(val versions: List<PlannedVersion>) {

    val canonicalVersions: List<PlannedVersion> = versions.filter { it.canonical }

    fun canonicalOf(version: PlannedVersion): PlannedVersion =
        canonicalVersions.first { it.folder == version.folder }
}

object VersionPlanner {

    private val RELEASE_VERSION = Regex("""\d+\.\d+(\.\d+)?""")

    fun plan(data: MinecraftData, versionFilter: List<String>): GenerationPlan {
        val pcPaths = data.dataPaths["pc"]!!.jsonObject

        val folderByVersion = pcPaths.entries
            .filter { (version, paths) -> RELEASE_VERSION.matches(version) && "protocol" in paths.jsonObject }
            .associate { (version, paths) -> version to paths.jsonObject["protocol"]!!.jsonPrimitive.content }

        val protocolNumbers = data.protocolNumbers()

        // Release order == semantic version order for release versions.
        val ordered = folderByVersion.keys.sortedBy { version ->
            val parts = version.split('.').map { it.toInt() }
            parts[0] * 1_000_000 + parts[1] * 1_000 + (parts.getOrNull(2) ?: 0)
        }

        // The canonical version of a folder: the version named like the folder itself, if present,
        // otherwise the oldest version using it.
        val canonicalByFolder = mutableMapOf<String, String>()
        for (version in ordered) {
            val folder = folderByVersion.getValue(version)
            if (folder.substringAfterLast('/') == version) {
                canonicalByFolder[folder] = version
            } else {
                canonicalByFolder.putIfAbsent(folder, version)
            }
        }

        var versions = ordered.map { version ->
            val folder = folderByVersion.getValue(version)
            PlannedVersion(
                version = version,
                // Versions absent from protocolVersions.json (e.g. "1.7") report the protocol
                // number of the data folder they point at.
                protocolNumber = protocolNumbers[version] ?: data.folderProtocolNumber(folder),
                folder = folder,
                canonical = canonicalByFolder[folder] == version,
            )
        }

        if (versionFilter.isNotEmpty()) {
            versions = versions.filter { it.version in versionFilter }
            // Make sure every folder still has a canonical version within the filtered set.
            val seen = mutableSetOf<String>()
            versions = versions.map { v ->
                if (v.folder in seen) v.copy(canonical = false) else { seen.add(v.folder); v.copy(canonical = true) }
            }
        }

        return GenerationPlan(versions)
    }
}
