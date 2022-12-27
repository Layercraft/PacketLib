package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Chat Command | 0x03 | play | serverbound
 *
 * @property command command
 * @property timestamp timestamp
 * @property salt salt
 * @property signedPreview signedPreview
 * @see <a href="https://wiki.vg/Protocol#Chat_Command">https://wiki.vg/Protocol#Chat_Command</a>
 */

@MinecraftPacket(id = 0x03, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class ChatCommandPacket(
    val command: String,
    val timestamp: Long,
    val salt: Long,
    val signedPreview: Boolean,
) : ServerBoundPacket {

    companion object : PacketSerializer<ChatCommandPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ChatCommandPacket {
            val command = input.readString()
            val timestamp = input.readLong()
            val salt = input.readLong()
            val signedPreview = input.readBoolean()

            return ChatCommandPacket(command, timestamp, salt, signedPreview)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ChatCommandPacket) {
            output.writeString(value.command)
            output.writeLong(value.timestamp)
            output.writeLong(value.salt)
            output.writeBoolean(value.signedPreview)
        }
    }
}