package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Open Book | 0x2b | play | clientbound
 *
 * @param hand hand
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Open_Book">https://wiki.vg/Protocol#Open_Book</a>
 */

data class OpenBookPacket(
    val hand: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<OpenBookPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): OpenBookPacket {
            val hand = input.readVarInt()

            return OpenBookPacket(hand)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: OpenBookPacket) {
            output.writeVarInt(value.hand)
        }
    }
}