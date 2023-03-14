package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

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
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Teleport_Entity">https://wiki.vg/Protocol#Teleport_Entity</a>
 */

@MinecraftPacket(id = 0x64, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EntityTeleportPacket {
            val entityId = input.readVarInt()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val yaw = input.readByte()
            val pitch = input.readByte()
            val onGround = input.readBoolean()

            return EntityTeleportPacket(entityId, x, y, z, yaw, pitch, onGround)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityTeleportPacket) {
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