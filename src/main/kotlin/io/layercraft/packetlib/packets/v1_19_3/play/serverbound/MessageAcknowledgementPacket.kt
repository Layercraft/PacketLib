package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Message Acknowledgment | 0x03 | play | serverbound
 *
 * @param count count
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Message_Acknowledgment">https://wiki.vg/Protocol#Message_Acknowledgment</a>
 */

@MinecraftPacket(id = 0x03, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class MessageAcknowledgementPacket(
    val count: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<MessageAcknowledgementPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): MessageAcknowledgementPacket {
            val count = input.readVarInt()

            return MessageAcknowledgementPacket(count)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: MessageAcknowledgementPacket) {
            output.writeVarInt(value.count)
        }
    }
}