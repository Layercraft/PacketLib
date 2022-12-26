package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Chat Suggestions | 0x15 | play | clientbound
 *
 * @property action action
 * @see <a href="https://wiki.vg/Protocol#Chat_Suggestions">https://wiki.vg/Protocol#Chat_Suggestions</a>
 */

@MinecraftPacket(packetId = 0x15, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ChatSuggestionsPacket(
    val action: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<ChatSuggestionsPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ChatSuggestionsPacket {
            val action = input.readVarInt()

            return ChatSuggestionsPacket(action)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ChatSuggestionsPacket) {
            output.writeVarInt(value.action)
        }
    }
}