package io.layercraft.translator.packets.play.data.bossbar

enum class BossBarFlag {

    DARKEN_SKY,
    DRAGON_BAR,
    FOG;

    companion object {
        fun fromFlag(flag: Int): BossBarFlag {
            return when (flag) {
                1 -> DARKEN_SKY
                2 -> DRAGON_BAR
                4 -> FOG
                else -> throw IllegalArgumentException("Invalid flag: $flag")
            }
        }
    }
}