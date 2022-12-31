package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Message Acknowledgment | 0x03 | play | serverbound
 *
 * @property hasLastMessage lastMessage is present
 * @property sender sender
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Message_Acknowledgment">https://wiki.vg/Protocol#Message_Acknowledgment</a>
 */

@MinecraftPacket(id = 0x03, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class MessageAcknowledgementPacket(
    val hasLastMessage: Boolean,
    val sender: UUID?,
) : ServerBoundPacket {
    companion object : PacketSerializer<MessageAcknowledgementPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): MessageAcknowledgementPacket {
            val hasLastMessage = input.readBoolean()
            val sender = if (hasLastMessage) input.readUUID() else null

            return MessageAcknowledgementPacket(hasLastMessage, sender)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: MessageAcknowledgementPacket) {
            output.writeBoolean(value.hasLastMessage)
            if (value.hasLastMessage) output.writeUUID(value.sender!!)
        }
    }
}
