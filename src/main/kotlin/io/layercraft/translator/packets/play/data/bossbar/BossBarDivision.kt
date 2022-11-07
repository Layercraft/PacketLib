package io.layercraft.translator.packets.play.data.bossbar

enum class BossBarDivision {

    NO,
    NOTCHES_6,
    NOTCHES_10,
    NOTCHES_12,
    NOTCHES_20;

    companion object {
        fun fromDivisionId(division: Int): BossBarDivision {
            return when (division) {
                0 -> NO
                1 -> NOTCHES_6
                2 -> NOTCHES_10
                3 -> NOTCHES_12
                4 -> NOTCHES_20
                else -> throw IllegalArgumentException("Invalid division id: $division")
            }
        }
    }

}