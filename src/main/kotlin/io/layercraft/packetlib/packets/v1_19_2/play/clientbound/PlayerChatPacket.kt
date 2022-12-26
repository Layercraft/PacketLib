package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID

/**
 * Player Chat Message | 0x33 | play | clientbound
 *
 * @property senderUuid senderUuid
 * @property plainMessage plainMessage
 * @property timestamp timestamp
 * @property salt salt
 * @property filterType filterType
 * @property type type
 * @property networkName networkName
 * @see <a href="https://wiki.vg/Protocol#Player_Chat_Message">https://wiki.vg/Protocol#Player_Chat_Message</a>
 */

@MinecraftPacket(packetId = 0x33, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class PlayerChatPacket(
    val senderUuid: UUID,
    val plainMessage: String,
    val timestamp: Long,
    val salt: Long,
    val filterType: Int, // varint
    val type: Int, // varint
    val networkName: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<PlayerChatPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): PlayerChatPacket {
            val senderUuid = input.readUUID()
            val plainMessage = input.readString()
            val timestamp = input.readLong()
            val salt = input.readLong()
            val filterType = input.readVarInt()
            val type = input.readVarInt()
            val networkName = input.readString()

            return PlayerChatPacket(senderUuid, plainMessage, timestamp, salt, filterType, type, networkName)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PlayerChatPacket) {
            output.writeUUID(value.senderUuid)
            output.writeString(value.plainMessage)
            output.writeLong(value.timestamp)
            output.writeLong(value.salt)
            output.writeVarInt(value.filterType)
            output.writeVarInt(value.type)
            output.writeString(value.networkName)
        }
    }
}