package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Border Center | 0x43 | play | clientbound
 *
 * @param x x
 * @param z z
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Border_Center">https://wiki.vg/Protocol#Set_Border_Center</a>
 */

data class WorldBorderCenterPacket(
    val x: Double,
    val z: Double,
) : ClientBoundPacket {
    companion object : PacketSerializer<WorldBorderCenterPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): WorldBorderCenterPacket {
            val x = input.readDouble()
            val z = input.readDouble()

            return WorldBorderCenterPacket(x, z)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: WorldBorderCenterPacket) {
            output.writeDouble(value.x)
            output.writeDouble(value.z)
        }
    }
}