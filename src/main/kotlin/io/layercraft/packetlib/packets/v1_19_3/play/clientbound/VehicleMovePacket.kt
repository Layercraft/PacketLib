package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Move Vehicle | 0x2a | play | clientbound
 *
 * @param x x
 * @param y y
 * @param z z
 * @param yaw yaw
 * @param pitch pitch
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Move_Vehicle">https://wiki.vg/Protocol#Move_Vehicle</a>
 */

data class VehicleMovePacket(
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float,
) : ClientBoundPacket {
    companion object : PacketSerializer<VehicleMovePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): VehicleMovePacket {
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val yaw = input.readFloat()
            val pitch = input.readFloat()

            return VehicleMovePacket(x, y, z, yaw, pitch)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: VehicleMovePacket) {
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeFloat(value.yaw)
            output.writeFloat(value.pitch)
        }
    }
}