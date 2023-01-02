package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Chat Message | 0x05 | play | serverbound
 *
 * @param message message
 * @param timestamp timestamp
 * @param salt salt
 * @param signature signature
 * @param signedPreview signedPreview
 * @property hasLastMessage lastMessage is present
 * @param lastMessageUser lastMessageUser
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Chat_Message">https://wiki.vg/Protocol#Chat_Message</a>
 */

@MinecraftPacket(id = 0x05, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class ChatMessagePacket(
    val message: String,
    val timestamp: Long,
    val salt: Long,
    val signature: ByteArray,
    val signedPreview: Boolean,
    val hasLastMessage: Boolean,
    val lastMessageUser: UUID?,
) : ServerBoundPacket {
    companion object : PacketSerializer<ChatMessagePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ChatMessagePacket {
            val message = input.readString()
            val timestamp = input.readLong()
            val salt = input.readLong()
            val signature = input.readVarIntByteArray()
            val signedPreview = input.readBoolean()
            val hasLastMessage = input.readBoolean()
            val lastMessageUser = if (hasLastMessage) input.readUUID() else null

            return ChatMessagePacket(message, timestamp, salt, signature, signedPreview, hasLastMessage, lastMessageUser)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ChatMessagePacket) {
            output.writeString(value.message)
            output.writeLong(value.timestamp)
            output.writeLong(value.salt)
            output.writeVarIntByteArray(value.signature)
            output.writeBoolean(value.signedPreview)
            output.writeBoolean(value.hasLastMessage)
            if (value.hasLastMessage) output.writeUUID(value.lastMessageUser!!)
        }
    }
}