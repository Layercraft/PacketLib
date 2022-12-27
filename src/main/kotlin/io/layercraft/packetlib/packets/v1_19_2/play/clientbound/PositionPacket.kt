package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Synchronize Player Position | 0x39 | play | clientbound
 *
 * @property x x
 * @property y y
 * @property z z
 * @property yaw yaw
 * @property pitch pitch
 * @property flags flags
 * @property teleportId teleportId
 * @property dismountVehicle dismountVehicle
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Synchronize_Player_Position">https://wiki.vg/Protocol#Synchronize_Player_Position</a>
 */

@MinecraftPacket(id = 0x39, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class PositionPacket(
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float,
    val flags: Byte,
    val teleportId: Int, // varint
    val dismountVehicle: Boolean,
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
            val dismountVehicle = input.readBoolean()

            return PositionPacket(x, y, z, yaw, pitch, flags, teleportId, dismountVehicle)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PositionPacket) {
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeFloat(value.yaw)
            output.writeFloat(value.pitch)
            output.writeByte(value.flags)
            output.writeVarInt(value.teleportId)
            output.writeBoolean(value.dismountVehicle)
        }
    }
}