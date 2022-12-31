package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Message Header | 0x32 | play | clientbound
 *
 * @property senderUuid senderUuid
 * @property headerSignature headerSignature
 * @property bodyDigest bodyDigest
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Message_Header">https://wiki.vg/Protocol#Message_Header</a>
 */

@MinecraftPacket(id = 0x32, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class MessageHeaderPacket(
    val senderUuid: UUID,
    val headerSignature: List<UByte>, // varint array
    val bodyDigest: List<UByte>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<MessageHeaderPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): MessageHeaderPacket {
            val senderUuid = input.readUUID()
            val headerSignature = input.readVarIntArray { arrayInput -> arrayInput.readUByte() }
            val bodyDigest = input.readVarIntArray { arrayInput -> arrayInput.readUByte() }

            return MessageHeaderPacket(senderUuid, headerSignature, bodyDigest)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: MessageHeaderPacket) {
            output.writeUUID(value.senderUuid)
            output.writeVarIntArray(value.headerSignature) { arrayValue, arrayOutput -> arrayOutput.writeUByte(arrayValue)}
            output.writeVarIntArray(value.bodyDigest) { arrayValue, arrayOutput -> arrayOutput.writeUByte(arrayValue)}
        }
    }
}
