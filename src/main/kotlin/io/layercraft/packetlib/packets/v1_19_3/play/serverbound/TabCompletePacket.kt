package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Command Suggestions Request | 0x08 | play | serverbound
 *
 * @param transactionId transactionId
 * @param text text
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Command_Suggestions_Request">https://wiki.vg/Protocol#Command_Suggestions_Request</a>
 */

data class TabCompletePacket(
    val transactionId: Int, // varint
    val text: String,
) : ServerBoundPacket {
    companion object : PacketSerializer<TabCompletePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): TabCompletePacket {
            val transactionId = input.readVarInt()
            val text = input.readString()

            return TabCompletePacket(transactionId, text)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: TabCompletePacket) {
            output.writeVarInt(value.transactionId)
            output.writeString(value.text)
        }
    }
}