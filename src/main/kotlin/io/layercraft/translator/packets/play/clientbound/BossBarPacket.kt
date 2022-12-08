package io.layercraft.translator.packets.play.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.packets.play.data.bossbar.BossBarAction
import io.layercraft.translator.packets.play.data.bossbar.BossBarAction.*
import io.layercraft.translator.packets.play.data.bossbar.BossBarColor
import io.layercraft.translator.packets.play.data.bossbar.BossBarDivision
import io.layercraft.translator.packets.play.data.bossbar.BossBarFlag
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface
import java.util.*

/**
 * Boss bar | 0x0A | play | client-bound
 *
 * @property uuid Unique ID for this bar.
 * @property action Determines the layout of the remaining packet.
 * @property title
 * @property health From 0 to 1. Values greater than 1 do not crash a Notchian client, and start rendering part of a second health bar at around 1.5.
 * @property color Color ID (see below).
 * @property division Type of division (see below).
 * @property flags Bit mask. 0x1: should darken sky, 0x2: is dragon bar (used to play end music), 0x04: create fog (previously was also controlled by 0x02).
 * @see <a href="https://wiki.vg/Protocol#Boss_Bar">https://wiki.vg/Protocol#Boss_Bar</a>
 */
@MinecraftPacket(0x0A, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class BossBarPacket(
    val uuid: UUID,
    val action: BossBarAction,
    val title: String?,
    val health: Float?,
    val color: BossBarColor?,
    val division: BossBarDivision?,
    val flags: Set<BossBarFlag>?,
) : ClientBoundPacket {
    companion object : PacketSerializer<BossBarPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): BossBarPacket {
            val uuid = input.readUUID()

            return when (val action = BossBarAction.byId(input.readVarInt())) {
                ADD -> {
                    val title = input.readChat()
                    val health = input.readFloat()
                    val color = BossBarColor.byId(input.readVarInt())
                    val division = BossBarDivision.byId(input.readVarInt())
                    val flags = BossBarFlag.fromUByteBitmask(input.readUByte())

                    BossBarPacket(uuid, action, title, health, color, division, flags)
                }

                REMOVE -> {
                    BossBarPacket(uuid, action, null, null, null, null, null)
                }
                UPDATE_HEALTH -> {
                    val health = input.readFloat()

                    BossBarPacket(uuid, action, null, health, null, null, null)
                }
                UPDATE_TITLE -> {
                    val title = input.readChat()

                    BossBarPacket(uuid, action, title, null, null, null, null)
                }
                UPDATE_STYLE -> {
                    val color = BossBarColor.byId(input.readVarInt())
                    val division = BossBarDivision.byId(input.readVarInt())

                    BossBarPacket(uuid, action, null, null, color, division, null)
                }
                UPDATE_FLAGS -> {
                    val flags = BossBarFlag.fromUByteBitmask(input.readUByte())

                    BossBarPacket(uuid, action, null, null, null, null, flags)
                }
                else -> throw IllegalArgumentException("Unknown boss bar action: $action")
            }
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: BossBarPacket) {
            output.writeUUID(value.uuid)
            output.writeVarInt(value.action.id)

            when (value.action) {
                ADD -> {
                    output.writeChat(value.title!!)
                    output.writeFloat(value.health!!)
                    output.writeVarInt(value.color!!.id)
                    output.writeVarInt(value.division!!.id)
                    output.writeUByte(BossBarFlag.toUByteBitmask(value.flags!!))
                }
                REMOVE -> {}
                UPDATE_HEALTH -> {
                    output.writeFloat(value.health!!)
                }
                UPDATE_TITLE -> {
                    output.writeChat(value.title!!)
                }
                UPDATE_STYLE -> {
                    output.writeVarInt(value.color!!.id)
                    output.writeVarInt(value.division!!.id)
                }
                UPDATE_FLAGS -> {
                    output.writeUByte(BossBarFlag.toUByteBitmask(value.flags!!))
                }
            }
        }
    }
}