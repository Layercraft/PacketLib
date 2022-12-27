package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x3c | play | clientbound
 *
 * @property entityId entityId
 * @property headYaw headYaw
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x3c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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