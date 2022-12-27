package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Entity Head Look | 0x34 | play | clientbound
 *
 * @property entityId entityId
 * @property headYaw headYaw
 * @see <a href="https://wiki.vg/Protocol#Entity_Head_Look">https://wiki.vg/Protocol#Entity_Head_Look</a>
 */

@MinecraftPacket(id = 0x34, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityHeadRotationPacket(
    val entityId: Int, // varint
    val headYaw: Byte,
) : ClientBoundPacket {

    companion object : PacketSerializer<EntityHeadRotationPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityHeadRotationPacket {
            val entityId = input.readVarInt()
            val headYaw = input.readByte()

            return EntityHeadRotationPacket(entityId, headYaw)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityHeadRotationPacket) {
            output.writeVarInt(value.entityId)
            output.writeByte(value.headYaw)
        }
    }
}
