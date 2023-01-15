package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.MinecraftPacket
import io.layercraft.packetlib.packets.PacketDirection
import io.layercraft.packetlib.packets.PacketSerializer
import io.layercraft.packetlib.packets.PacketState
import io.layercraft.packetlib.packets.ServerBoundPacket
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.*
/**
 * Chat Message | 0x05 | play | serverbound
 *
 * @param message message
 * @param timestamp timestamp
 * @param salt salt
 * @param signature signature
 * @param signedPreview signedPreview
 * @property hasLastRejectedMessage lastRejectedMessage is present
 * @param sender sender
 * @param signature signature
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Chat_Message">https://wiki.vg/Protocol#Chat_Message</a>
 */

@MinecraftPacket(id = 0x05, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class ChatMessagePacket(
    val message: String,
    val timestamp: Long,
    val salt: Long,
    val signature: ByteArray,
    val signedPreview: Boolean,
    val hasLastRejectedMessage: Boolean,
    val sender: UUID?,
    val signature2: ByteArray?,
) : ServerBoundPacket {
    companion object : PacketSerializer<ChatMessagePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ChatMessagePacket {
            val message = input.readString()
            val timestamp = input.readLong()
            val salt = input.readLong()
            val signature = input.readVarIntByteArray()
            val signedPreview = input.readBoolean()
            val hasLastRejectedMessage = input.readBoolean()
            val sender = if (hasLastRejectedMessage) input.readUUID() else null
            val signature2 = if (hasLastRejectedMessage) input.readVarIntByteArray() else null

            return ChatMessagePacket(message, timestamp, salt, signature, signedPreview, hasLastRejectedMessage, sender, signature2)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ChatMessagePacket) {
            output.writeString(value.message)
            output.writeLong(value.timestamp)
            output.writeLong(value.salt)
            output.writeVarIntByteArray(value.signature)
            output.writeBoolean(value.signedPreview)
            output.writeBoolean(value.hasLastRejectedMessage)
            if (value.hasLastRejectedMessage) output.writeUUID(value.sender!!)
            if (value.hasLastRejectedMessage) output.writeVarIntByteArray(value.signature2!!)
        }
    }
}