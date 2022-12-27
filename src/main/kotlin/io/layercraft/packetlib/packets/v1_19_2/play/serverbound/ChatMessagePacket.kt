package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Chat Message | 0x05 | play | serverbound
 *
 * @property message message
 * @property timestamp timestamp
 * @property salt salt
 * @property signedPreview signedPreview
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Chat_Message">https://wiki.vg/Protocol#Chat_Message</a>
 */

@MinecraftPacket(id = 0x05, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
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