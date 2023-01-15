package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Message Acknowledgment | 0x03 | play | serverbound
 *
 * @property hasLastRejectedMessage lastRejectedMessage is present
 * @param sender sender
 * @param signature signature
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Message_Acknowledgment">https://wiki.vg/Protocol#Message_Acknowledgment</a>
 */

@MinecraftPacket(id = 0x03, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class MessageAcknowledgementPacket(
    val hasLastRejectedMessage: Boolean,
    val sender: UUID?,
    val signature: ByteArray?,
) : ServerBoundPacket {
    companion object : PacketSerializer<MessageAcknowledgementPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): MessageAcknowledgementPacket {
            val hasLastRejectedMessage = input.readBoolean()
            val sender = if (hasLastRejectedMessage) input.readUUID() else null
            val signature = if (hasLastRejectedMessage) input.readVarIntByteArray() else null

            return MessageAcknowledgementPacket(hasLastRejectedMessage, sender, signature)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: MessageAcknowledgementPacket) {
            output.writeBoolean(value.hasLastRejectedMessage)
            if (value.hasLastRejectedMessage) output.writeUUID(value.sender!!)
            if (value.hasLastRejectedMessage) output.writeVarIntByteArray(value.signature!!)
        }
    }
}