package io.layercraft.translator.packets.play.data.bossbar

enum class BossBarColor(
    val id: Int,
) {
    PINK(0),
    BLUE(1),
    RED(2),
    GREEN(3),
    YELLOW(4),
    PURPLE(5),
    WHITE(6),
    ;

    companion object {
        private val BY_ID = values().associateBy(BossBarColor::id)

        fun byId(id: Int) = BY_ID[id] ?: throw IllegalArgumentException("Unknown boss bar color id: $id")
    }
}