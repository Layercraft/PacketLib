package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Clear Titles | 0x0c | play | clientbound
 *
 * @param reset reset
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Clear_Titles">https://wiki.vg/Protocol#Clear_Titles</a>
 */

data class ClearTitlesPacket(
    val reset: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<ClearTitlesPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): ClearTitlesPacket {
            val reset = input.readBoolean()

            return ClearTitlesPacket(reset)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: ClearTitlesPacket) {
            output.writeBoolean(value.reset)
        }
    }
}