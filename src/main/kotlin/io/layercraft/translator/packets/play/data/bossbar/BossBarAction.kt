package io.layercraft.translator.packets.play.data.bossbar

enum class BossBarAction {

    ADD,
    REMOVE,
    UPDATE_HEALTH,
    UPDATE_TITLE,
    UPDATE_STYLE,
    UPDATE_FLAGS;

    companion object {
        fun fromActionId(id: Int): BossBarAction {
            return when (id) {
                0 -> ADD
                1 -> REMOVE
                2 -> UPDATE_HEALTH
                3 -> UPDATE_TITLE
                4 -> UPDATE_STYLE
                5 -> UPDATE_FLAGS
                else -> throw IllegalArgumentException("Invalid action id: $id")
            }
        }
    }

}