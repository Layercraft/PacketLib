package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Chat Suggestions | 0x14 | play | clientbound
 *
 * @param action action
 * @param entries entries
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Chat_Suggestions">https://wiki.vg/Protocol#Chat_Suggestions</a>
 */

@MinecraftPacket(id = 0x14, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ChatSuggestionsPacket(
    val action: Int, // varint
    val entries: List<String>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<ChatSuggestionsPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ChatSuggestionsPacket {
            val action = input.readVarInt()
            val entries = input.readVarIntArray { arrayInput -> arrayInput.readString() }

            return ChatSuggestionsPacket(action, entries)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ChatSuggestionsPacket) {
            output.writeVarInt(value.action)
            output.writeVarIntArray(value.entries) { arrayValue, arrayOutput -> arrayOutput.writeString(arrayValue) }
        }
    }
}