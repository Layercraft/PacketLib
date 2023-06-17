package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Border Size | 0x45 | play | clientbound
 *
 * @param diameter diameter
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Border_Size">https://wiki.vg/Protocol#Set_Border_Size</a>
 */

data class WorldBorderSizePacket(
    val diameter: Double,
) : ClientBoundPacket {
    companion object : PacketSerializer<WorldBorderSizePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): WorldBorderSizePacket {
            val diameter = input.readDouble()

            return WorldBorderSizePacket(diameter)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: WorldBorderSizePacket) {
            output.writeDouble(value.diameter)
        }
    }
}