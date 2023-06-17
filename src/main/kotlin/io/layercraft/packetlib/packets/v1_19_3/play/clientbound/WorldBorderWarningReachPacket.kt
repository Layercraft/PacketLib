package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Border Warning Distance | 0x47 | play | clientbound
 *
 * @param warningBlocks warningBlocks
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Border_Warning_Distance">https://wiki.vg/Protocol#Set_Border_Warning_Distance</a>
 */

data class WorldBorderWarningReachPacket(
    val warningBlocks: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<WorldBorderWarningReachPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): WorldBorderWarningReachPacket {
            val warningBlocks = input.readVarInt()

            return WorldBorderWarningReachPacket(warningBlocks)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: WorldBorderWarningReachPacket) {
            output.writeVarInt(value.warningBlocks)
        }
    }
}