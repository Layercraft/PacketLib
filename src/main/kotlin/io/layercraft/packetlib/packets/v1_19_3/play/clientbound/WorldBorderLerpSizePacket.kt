package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Border Lerp Size | 0x44 | play | clientbound
 *
 * @param oldDiameter oldDiameter
 * @param newDiameter newDiameter
 * @param speed speed
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Border_Lerp_Size">https://wiki.vg/Protocol#Set_Border_Lerp_Size</a>
 */

data class WorldBorderLerpSizePacket(
    val oldDiameter: Double,
    val newDiameter: Double,
    val speed: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<WorldBorderLerpSizePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): WorldBorderLerpSizePacket {
            val oldDiameter = input.readDouble()
            val newDiameter = input.readDouble()
            val speed = input.readVarInt()

            return WorldBorderLerpSizePacket(oldDiameter, newDiameter, speed)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: WorldBorderLerpSizePacket) {
            output.writeDouble(value.oldDiameter)
            output.writeDouble(value.newDiameter)
            output.writeVarInt(value.speed)
        }
    }
}