package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Tab List Header And Footer | 0x61 | play | clientbound
 *
 * @param header header
 * @param footer footer
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Tab_List_Header_And_Footer">https://wiki.vg/Protocol#Set_Tab_List_Header_And_Footer</a>
 */

data class PlayerlistHeaderPacket(
    val header: String,
    val footer: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<PlayerlistHeaderPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): PlayerlistHeaderPacket {
            val header = input.readString()
            val footer = input.readString()

            return PlayerlistHeaderPacket(header, footer)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: PlayerlistHeaderPacket) {
            output.writeString(value.header)
            output.writeString(value.footer)
        }
    }
}