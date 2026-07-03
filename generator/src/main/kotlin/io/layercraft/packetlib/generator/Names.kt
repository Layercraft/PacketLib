package io.layercraft.packetlib.generator

private val WORD_SEPARATOR = Regex("[-_ ]([a-zA-Z0-9])")

/** `spawn_entity` -> `spawnEntity`, `UUID` -> `uuid` */
fun camelCase(name: String): String {
    var cleaned = name.replace(Regex("[^A-Za-z0-9_\\- ]"), "_")
    if (cleaned.isEmpty() || cleaned.first().isDigit()) cleaned = "f$cleaned"
    if (cleaned.all { it.isUpperCase() || !it.isLetter() }) return cleaned.lowercase().replace(Regex("[\\- ]"), "_")
    val replaced = WORD_SEPARATOR.replace(cleaned) { it.groupValues[1].uppercase() }
    return replaced.replaceFirstChar { it.lowercaseChar() }
}

/** `spawn_entity` -> `SpawnEntity` */
fun pascalCase(name: String): String = camelCase(name).replaceFirstChar { it.uppercaseChar() }

/** `1.19.3` -> `v1_19_3` */
fun versionPackage(version: String): String = "v" + version.replace(".", "_")

/** `1.19.3` -> `V1_19_3` */
fun versionEnumName(version: String): String = "V" + version.replace(".", "_")

/** Allocates unique simple names within one scope (class file, function body, ...). */
class UniqueNames {
    private val used = mutableSetOf<String>()

    fun claim(base: String): String {
        var candidate = base
        var i = 1
        while (!used.add(candidate)) {
            candidate = base + i
            i++
        }
        return candidate
    }
}
