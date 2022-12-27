package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Entity Look | 0x27 | play | clientbound
 *
 * @property entityId entityId
 * @property yaw yaw
 * @property pitch pitch
 * @property onGround onGround
 * @see <a href="https://wiki.vg/Protocol#Entity_Look">https://wiki.vg/Protocol#Entity_Look</a>
 */

@MinecraftPacket(id = 0x27, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityLookPacket(
    val entityId: Int, // varint
    val yaw: Byte,
    val pitch: Byte,
    val onGround: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<EntityLookPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityLookPacket {
            val entityId = input.readVarInt()
            val yaw = input.readByte()
            val pitch = input.readByte()
            val onGround = input.readBoolean()

            return EntityLookPacket(entityId, yaw, pitch, onGround)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityLookPacket) {
            output.writeVarInt(value.entityId)
            output.writeByte(value.yaw)
            output.writeByte(value.pitch)
            output.writeBoolean(value.onGround)
        }
    }
}
