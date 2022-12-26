package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID

/**
 * Player Chat Message | 0x30 | play | clientbound
 *
 * @property signedChatContent signedChatContent
 * @property type type
 * @property senderUuid senderUuid
 * @property senderName senderName
 * @property timestamp timestamp
 * @property salt salt
 * @see <a href="https://wiki.vg/Protocol#Player_Chat_Message">https://wiki.vg/Protocol#Player_Chat_Message</a>
 */

@MinecraftPacket(packetId = 0x30, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class PlayerChatPacket(
    val signedChatContent: String,
    val type: Int, // varint
    val senderUuid: UUID,
    val senderName: String,
    val timestamp: Long,
    val salt: Long,
) : ClientBoundPacket {

    companion object : PacketSerializer<PlayerChatPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): PlayerChatPacket {
            val signedChatContent = input.readString()
            val type = input.readVarInt()
            val senderUuid = input.readUUID()
            val senderName = input.readString()
            val timestamp = input.readLong()
            val salt = input.readLong()

            return PlayerChatPacket(signedChatContent, type, senderUuid, senderName, timestamp, salt)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PlayerChatPacket) {
            output.writeString(value.signedChatContent)
            output.writeVarInt(value.type)
            output.writeUUID(value.senderUuid)
            output.writeString(value.senderName)
            output.writeLong(value.timestamp)
            output.writeLong(value.salt)
        }
    }
}