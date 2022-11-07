package io.layercraft.translator.packets.play.clientbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.packets.handshake.data.HandshakeNextState
import io.layercraft.translator.packets.play.data.bossbar.BossBarAction
import io.layercraft.translator.packets.play.data.bossbar.BossBarAction.*
import io.layercraft.translator.packets.play.data.bossbar.BossBarColor
import io.layercraft.translator.packets.play.data.bossbar.BossBarDivision
import io.layercraft.translator.packets.play.data.bossbar.BossBarFlag
import io.layercraft.translator.types.Position
import io.layercraft.translator.utils.mc
import java.util.UUID

/**
 * Block bar | 0x0A | play | client-bound
 *
 * Send a boss bar to the player.
 *
 * @see <a href="https://wiki.vg/Protocol#Block_Action">https://wiki.vg/Protocol#Block_Action</a>
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
        override fun serialize(input: Input): BossBar {
            val uuid = input.mc.readUUID()
            val action = BossBarAction.fromActionId(input.mc.readVarInt())

            when (action) {
                ADD -> {
                    val title = input.mc.readString()
                    val health = input.mc.readFloat()
                    val color = BossBarColor.fromColorId(input.mc.readVarInt())
                    val division = BossBarDivision.fromDivisionId(input.mc.readVarInt())


                }

                REMOVE -> TODO()
                UPDATE_HEALTH -> TODO()
                UPDATE_TITLE -> TODO()
                UPDATE_STYLE -> TODO()
                UPDATE_FLAGS -> TODO()
            }


        }

        override fun deserialize(output: Output, value: BossBar) {

        }

    }

}