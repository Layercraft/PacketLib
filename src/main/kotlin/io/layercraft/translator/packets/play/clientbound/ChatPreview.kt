package io.layercraft.translator.packets.play.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Chat preview | 0x0C | play | clientbound
 *
 * @property queryId
 * @property isPresent Component is Present
 * @property message Message to Preview
 * @see <a href="https://wiki.vg/Protocol#Chat_Preview">https://wiki.vg/Protocol#Chat_Preview</a>
 */
@MinecraftPacket(0x0C, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class ChatPreview(
    val queryId: Int,
    val isPresent: Boolean,
    val message: String?,
) : ClientBoundPacket {
    companion object: PacketSerializer<ChatPreview> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ChatPreview {
            val queryId = input.readVarInt()
            val isPresent = input.readBoolean()
            val message = if (isPresent) input.readChat() else null

            return ChatPreview(queryId, isPresent, message)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ChatPreview) {
            output.writeVarInt(value.queryId)
            output.writeBoolean(value.isPresent)
            if (value.isPresent) output.writeChat(value.message!!)
        }

    }
}
