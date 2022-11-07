package io.layercraft.translator.packets.play.data.bossbar

enum class BossBarColor {

    PINK,
    BLUE,
    RED,
    GREEN,
    YELLOW,
    PURPLE,
    WHITE;

    companion object {
        fun fromColorId(id: Int): BossBarColor {
            return when (id) {
                0 -> PINK
                1 -> BLUE
                2 -> RED
                3 -> GREEN
                4 -> YELLOW
                5 -> PURPLE
                6 -> WHITE
                else -> throw IllegalArgumentException("Invalid color id: $id")
            }
        }
    }

}