package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Entity Teleport | 0x49 | play | clientbound
 *
 * @property entityId entityId
 * @property x x
 * @property y y
 * @property z z
 * @property yaw yaw
 * @property pitch pitch
 * @property onGround onGround
 * @see <a href="https://wiki.vg/Protocol#Entity_Teleport">https://wiki.vg/Protocol#Entity_Teleport</a>
 */

@MinecraftPacket(packetId = 0x49, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityTeleportPacket(
    val entityId: Int, // varint
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Byte,
    val pitch: Byte,
    val onGround: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<EntityTeleportPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityTeleportPacket {
            val entityId = input.readVarInt()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val yaw = input.readByte()
            val pitch = input.readByte()
            val onGround = input.readBoolean()

            return EntityTeleportPacket(entityId, x, y, z, yaw, pitch, onGround)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityTeleportPacket) {
            output.writeVarInt(value.entityId)
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeByte(value.yaw)
            output.writeByte(value.pitch)
            output.writeBoolean(value.onGround)
        }
    }
}
