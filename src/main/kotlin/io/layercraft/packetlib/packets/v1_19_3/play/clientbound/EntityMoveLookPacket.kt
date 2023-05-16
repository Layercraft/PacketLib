package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Entity Position and Rotation | 0x28 | play | clientbound
 *
 * @param entityId entityId
 * @param dX dX
 * @param dY dY
 * @param dZ dZ
 * @param yaw yaw
 * @param pitch pitch
 * @param onGround onGround
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18071#Update_Entity_Position_and_Rotation">https://wiki.vg/Protocol#Update_Entity_Position_and_Rotation</a>
 */

@MinecraftPacket(id = 0x28, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityMoveLookPacket(
    val entityId: Int, // varint
    val dX: Short,
    val dY: Short,
    val dZ: Short,
    val yaw: Byte,
    val pitch: Byte,
    val onGround: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityMoveLookPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EntityMoveLookPacket {
            val entityId = input.readVarInt()
            val dX = input.readShort()
            val dY = input.readShort()
            val dZ = input.readShort()
            val yaw = input.readByte()
            val pitch = input.readByte()
            val onGround = input.readBoolean()

            return EntityMoveLookPacket(entityId, dX, dY, dZ, yaw, pitch, onGround)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityMoveLookPacket) {
            output.writeVarInt(value.entityId)
            output.writeShort(value.dX)
            output.writeShort(value.dY)
            output.writeShort(value.dZ)
            output.writeByte(value.yaw)
            output.writeByte(value.pitch)
            output.writeBoolean(value.onGround)
        }
    }
}