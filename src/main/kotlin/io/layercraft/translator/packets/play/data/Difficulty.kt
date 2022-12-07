package io.layercraft.translator.packets.play.data

enum class Difficulty(
    val id: Int,
) {
    PEACEFUL(0),
    EASY(1),
    NORMAL(2),
    HARD(3),
    ;

    companion object {
        private val BY_ID = values().associateBy(Difficulty::id)

        fun byId(id: Int) = BY_ID[id] ?: throw IllegalArgumentException("Unknown difficulty id: $id")
    }
}