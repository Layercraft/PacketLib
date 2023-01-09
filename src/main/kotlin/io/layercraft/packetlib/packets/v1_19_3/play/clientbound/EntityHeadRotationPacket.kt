package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Head Rotation | 0x3e | play | clientbound
 *
 * @param entityId entityId
 * @param headYaw headYaw
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Set_Head_Rotation">https://wiki.vg/Protocol#Set_Head_Rotation</a>
 */

@MinecraftPacket(id = 0x3e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityHeadRotationPacket(
    val entityId: Int, // varint
    val headYaw: Byte,
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityHeadRotationPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EntityHeadRotationPacket {
            val entityId = input.readVarInt()
            val headYaw = input.readByte()

            return EntityHeadRotationPacket(entityId, headYaw)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityHeadRotationPacket) {
            output.writeVarInt(value.entityId)
            output.writeByte(value.headYaw)
        }
    }
}