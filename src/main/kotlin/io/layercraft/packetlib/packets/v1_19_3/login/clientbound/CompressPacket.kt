package io.layercraft.packetlib.packets.v1_19_3.login.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Compression | 0x03 | login | clientbound
 *
 * @param threshold threshold
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Compression">https://wiki.vg/Protocol#Set_Compression</a>
 */

data class CompressPacket(
    val threshold: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<CompressPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): CompressPacket {
            val threshold = input.readVarInt()

            return CompressPacket(threshold)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: CompressPacket) {
            output.writeVarInt(value.threshold)
        }
    }
}