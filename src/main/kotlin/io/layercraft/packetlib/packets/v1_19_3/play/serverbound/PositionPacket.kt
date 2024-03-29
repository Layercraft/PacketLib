package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Player Position | 0x13 | play | serverbound
 *
 * @param x x
 * @param y y
 * @param z z
 * @param onGround onGround
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Player_Position">https://wiki.vg/Protocol#Set_Player_Position</a>
 */

data class PositionPacket(
    val x: Double,
    val y: Double,
    val z: Double,
    val onGround: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<PositionPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): PositionPacket {
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val onGround = input.readBoolean()

            return PositionPacket(x, y, z, onGround)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: PositionPacket) {
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeBoolean(value.onGround)
        }
    }
}