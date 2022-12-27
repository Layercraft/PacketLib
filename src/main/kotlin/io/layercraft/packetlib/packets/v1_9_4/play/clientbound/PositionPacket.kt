package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x2e | play | clientbound
 *
 * @property x x
 * @property y y
 * @property z z
 * @property yaw yaw
 * @property pitch pitch
 * @property flags flags
 * @property teleportId teleportId
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x2e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class PositionPacket(
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float,
    val flags: Byte,
    val teleportId: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<PositionPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): PositionPacket {
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val yaw = input.readFloat()
            val pitch = input.readFloat()
            val flags = input.readByte()
            val teleportId = input.readVarInt()

            return PositionPacket(x, y, z, yaw, pitch, flags, teleportId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PositionPacket) {
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeFloat(value.yaw)
            output.writeFloat(value.pitch)
            output.writeByte(value.flags)
            output.writeVarInt(value.teleportId)
        }
    }
}
