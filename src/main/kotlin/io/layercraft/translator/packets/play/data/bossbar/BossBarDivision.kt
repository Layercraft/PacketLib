package io.layercraft.translator.packets.play.data.bossbar

enum class BossBarDivision(
    val id: Int,
) {

    NO(0),
    NOTCHES_6(1),
    NOTCHES_10(2),
    NOTCHES_12(3),
    NOTCHES_20(4),
    ;

    companion object {
        private val BY_ID = values().associateBy(BossBarDivision::id)

        fun byId(id: Int) = BY_ID[id] ?: throw IllegalArgumentException("Unknown boss bar division id: $id")
    }
}