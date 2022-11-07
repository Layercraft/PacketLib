package io.layercraft.translator.packets.play.clientbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.packets.play.data.bossbar.BossBarAction
import io.layercraft.translator.packets.play.data.bossbar.BossBarAction.*
import io.layercraft.translator.packets.play.data.bossbar.BossBarColor
import io.layercraft.translator.packets.play.data.bossbar.BossBarDivision
import io.layercraft.translator.packets.play.data.bossbar.BossBarFlag
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.translator.utils.minecraft
import java.util.UUID

/**
 * Block bar | 0x0A | play | client-bound
 *
 * Send a boss bar to the player.
 *
 * @see <a href="https://wiki.vg/Protocol#Boss_Bar">https://wiki.vg/Protocol#Boss_Bar</a>
 */
@MinecraftPacket(0x0A, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class BossBar(
    val uuid: UUID,
    val action: BossBarAction,
    val title: String?,
    val health: Float?,
    val color: BossBarColor?,
    val division: BossBarDivision?,
    val flags: List<BossBarFlag>?

) : ClientBoundPacket {

    companion object : PacketSerializer<BossBar> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): BossBar {
            val uuid = input.readUUID()
            val action = BossBarAction.fromActionId(input.readVarInt())

            when (action) {
                ADD -> {
                    val title = input.readString()
                    val health = input.readFloat()
                    val color = BossBarColor.fromColorId(input.readVarInt())
                    val division = BossBarDivision.fromDivisionId(input.readVarInt())


                }

                REMOVE -> TODO()
                UPDATE_HEALTH -> TODO()
                UPDATE_TITLE -> TODO()
                UPDATE_STYLE -> TODO()
                UPDATE_FLAGS -> TODO()
            }

            throw UnsupportedOperationException("Boss bar action $action is not supported yet.")
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: BossBar) {

        }

    }

}