package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Chat Command | 0x04 | play | serverbound
 *
 * @param command command
 * @param timestamp timestamp
 * @param salt salt
 * @param argumentSignatures list of ChatCommandPacketArgumentSignatures
 * @param signedPreview signedPreview
 * @param hasLastRejectedMessage lastRejectedMessage is present
 * @param sender sender
 * @param signature signature
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Chat_Command">https://wiki.vg/Protocol#Chat_Command</a>
 */

@MinecraftPacket(id = 0x04, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class ChatCommandPacket(
    val command: String,
    val timestamp: Long,
    val salt: Long,
    val argumentSignatures: List<ChatCommandPacketArgumentSignatures>, // varint array
    val signedPreview: Boolean,
    val hasLastRejectedMessage: Boolean,
    val sender: UUID?,
    val signature: ByteArray?,
) : ServerBoundPacket {
    companion object : PacketSerializer<ChatCommandPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ChatCommandPacket {
            val command = input.readString()
            val timestamp = input.readLong()
            val salt = input.readLong()
            val argumentSignatures = input.readVarIntArray { arrayInput ->
                val argumentName = arrayInput.readString()
                val signature = arrayInput.readVarIntByteArray()

                return@readVarIntArray ChatCommandPacketArgumentSignatures(argumentName, signature)
            }
            val signedPreview = input.readBoolean()
            val hasLastRejectedMessage = input.readBoolean()
            val sender = if (hasLastRejectedMessage) input.readUUID() else null
            val signature = if (hasLastRejectedMessage) input.readVarIntByteArray() else null

            return ChatCommandPacket(command, timestamp, salt, argumentSignatures, signedPreview, hasLastRejectedMessage, sender, signature)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ChatCommandPacket) {
            output.writeString(value.command)
            output.writeLong(value.timestamp)
            output.writeLong(value.salt)

            output.writeVarIntArray(value.argumentSignatures) { arrayValue, arrayOutput ->
                arrayOutput.writeString(arrayValue.argumentName)
                arrayOutput.writeVarIntByteArray(arrayValue.signature)
            }

            output.writeBoolean(value.signedPreview)
            output.writeBoolean(value.hasLastRejectedMessage)
            if (value.hasLastRejectedMessage) output.writeUUID(value.sender!!)
            if (value.hasLastRejectedMessage) output.writeVarIntByteArray(value.signature!!)
        }
    }
}

/**
 * ChatCommandPacketArgumentSignatures
 *
 * @param argumentName argumentName
 * @param signature signature
*/
data class ChatCommandPacketArgumentSignatures(
    val argumentName: String,
    val signature: ByteArray,
)