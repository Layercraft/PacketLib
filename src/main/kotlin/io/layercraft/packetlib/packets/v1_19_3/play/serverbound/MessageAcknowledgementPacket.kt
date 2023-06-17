package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Message Acknowledgment | 0x03 | play | serverbound
 *
 * @param count count
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Message_Acknowledgment">https://wiki.vg/Protocol#Message_Acknowledgment</a>
 */

data class MessageAcknowledgementPacket(
    val count: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<MessageAcknowledgementPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): MessageAcknowledgementPacket {
            val count = input.readVarInt()

            return MessageAcknowledgementPacket(count)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: MessageAcknowledgementPacket) {
            output.writeVarInt(value.count)
        }
    }
}