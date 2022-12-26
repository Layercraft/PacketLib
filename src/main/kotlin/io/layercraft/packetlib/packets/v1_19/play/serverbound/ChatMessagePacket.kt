package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Chat Message | 0x04 | play | serverbound
 *
 * @property message message
 * @property timestamp timestamp
 * @property salt salt
 * @property signedPreview signedPreview
 * @see <a href="https://wiki.vg/Protocol#Chat_Message">https://wiki.vg/Protocol#Chat_Message</a>
 */

@MinecraftPacket(packetId = 0x04, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class ChatMessagePacket(
    val message: String,
    val timestamp: Long,
    val salt: Long,
    val signedPreview: Boolean,
) : ServerBoundPacket {

    companion object : PacketSerializer<ChatMessagePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ChatMessagePacket {
            val message = input.readString()
            val timestamp = input.readLong()
            val salt = input.readLong()
            val signedPreview = input.readBoolean()

            return ChatMessagePacket(message, timestamp, salt, signedPreview)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ChatMessagePacket) {
            output.writeString(value.message)
            output.writeLong(value.timestamp)
            output.writeLong(value.salt)
            output.writeBoolean(value.signedPreview)
        }
    }
}