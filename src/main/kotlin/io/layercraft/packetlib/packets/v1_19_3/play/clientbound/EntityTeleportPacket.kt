package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Teleport Entity | 0x64 | play | clientbound
 *
 * @param entityId entityId
 * @param x x
 * @param y y
 * @param z z
 * @param yaw yaw
 * @param pitch pitch
 * @param onGround onGround
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Teleport_Entity">https://wiki.vg/Protocol#Teleport_Entity</a>
 */

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
        override fun deserialize(input: MCProtocolDeserializer<*>): EntityTeleportPacket {
            val entityId = input.readVarInt()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val yaw = input.readByte()
            val pitch = input.readByte()
            val onGround = input.readBoolean()

            return EntityTeleportPacket(entityId, x, y, z, yaw, pitch, onGround)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: EntityTeleportPacket) {
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