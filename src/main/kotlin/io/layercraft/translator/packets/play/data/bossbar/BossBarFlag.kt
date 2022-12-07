package io.layercraft.translator.packets.play.data.bossbar

enum class BossBarFlag(
    val id: Int,
) {

    DARKEN_SKY(0x1),
    DRAGON_BAR(0x2),
    FOG(0x4),
    ;

    companion object {
        private val BY_ID = values().associateBy(BossBarFlag::id)

        fun byId(id: Int) = BY_ID[id] ?: throw IllegalArgumentException("Unknown boss bar flag id: $id")
        fun fromUByteBitmask(bitmask: UByte) = values().filter { bitmask and it.id.toUByte() != (0u).toUByte() }.toSet()
        fun toUByteBitmask(flags: Set<BossBarFlag>) = flags.fold((0u).toUByte()) { acc, flag -> acc or flag.id.toUByte() }
    }
}