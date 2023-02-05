package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Entity Rotation | 0x29 | play | clientbound
 *
 * @param entityId entityId
 * @param yaw yaw
 * @param pitch pitch
 * @param onGround onGround
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17964#Update_Entity_Rotation">https://wiki.vg/Protocol#Update_Entity_Rotation</a>
 */

@MinecraftPacket(id = 0x29, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityLookPacket(
    val entityId: Int, // varint
    val yaw: Byte,
    val pitch: Byte,
    val onGround: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityLookPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EntityLookPacket {
            val entityId = input.readVarInt()
            val yaw = input.readByte()
            val pitch = input.readByte()
            val onGround = input.readBoolean()

            return EntityLookPacket(entityId, yaw, pitch, onGround)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityLookPacket) {
            output.writeVarInt(value.entityId)
            output.writeByte(value.yaw)
            output.writeByte(value.pitch)
            output.writeBoolean(value.onGround)
        }
    }
}