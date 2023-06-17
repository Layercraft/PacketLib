package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Boss Bar | 0x0a | play | clientbound
 *
 * @param entityUUID entityUUID
 * @param action action
 * @param title title
 * @param health health
 * @param color color
 * @param dividers dividers
 * @param flags flags
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Boss_Bar">https://wiki.vg/Protocol#Boss_Bar</a>
 */

@MinecraftPacket(id = 0x0a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class BossBarPacket(
    val entityUUID: UUID,
    val action: Int, // varint
    val title: String?,
    val health: Float?,
    val color: Int?, // varint
    val dividers: Int?, // varint
    val flags: UByte?,
) : ClientBoundPacket {
    companion object : PacketSerializer<BossBarPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): BossBarPacket {
            val entityUUID = input.readUUID()
            val action = input.readVarInt()
            val title = when (action) {
                0 -> input.readString()
                3 -> input.readString()
                else -> null
            }
            val health = when (action) {
                0 -> input.readFloat()
                2 -> input.readFloat()
                else -> null
            }
            val color = when (action) {
                0 -> input.readVarInt()
                4 -> input.readVarInt()
                else -> null
            }
            val dividers = when (action) {
                0 -> input.readVarInt()
                4 -> input.readVarInt()
                else -> null
            }
            val flags = when (action) {
                0 -> input.readUByte()
                5 -> input.readUByte()
                else -> null
            }

            return BossBarPacket(entityUUID, action, title, health, color, dividers, flags)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: BossBarPacket) {
            output.writeUUID(value.entityUUID)
            output.writeVarInt(value.action)
            when (value.action) {
                0 -> output.writeString(value.title!!)
                3 -> output.writeString(value.title!!)
                else -> {}
            }
            when (value.action) {
                0 -> output.writeFloat(value.health!!)
                2 -> output.writeFloat(value.health!!)
                else -> {}
            }
            when (value.action) {
                0 -> output.writeVarInt(value.color!!)
                4 -> output.writeVarInt(value.color!!)
                else -> {}
            }
            when (value.action) {
                0 -> output.writeVarInt(value.dividers!!)
                4 -> output.writeVarInt(value.dividers!!)
                else -> {}
            }
            when (value.action) {
                0 -> output.writeUByte(value.flags!!)
                5 -> output.writeUByte(value.flags!!)
                else -> {}
            }
        }
    }
}