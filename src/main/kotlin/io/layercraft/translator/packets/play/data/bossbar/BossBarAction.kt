package io.layercraft.translator.packets.play.data.bossbar

enum class BossBarAction(
    val id: Int,
) {

    ADD(0),
    REMOVE(1),
    UPDATE_HEALTH(2),
    UPDATE_TITLE(3),
    UPDATE_STYLE(4),
    UPDATE_FLAGS(5),
    ;

    companion object {
        private val BY_ID = values().associateBy(BossBarAction::id)

        fun byId(id: Int) = BY_ID[id] ?: throw IllegalArgumentException("Unknown boss bar action id: $id")
    }
}