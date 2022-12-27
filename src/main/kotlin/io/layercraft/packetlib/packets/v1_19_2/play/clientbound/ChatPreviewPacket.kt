package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x0c | play | clientbound
 *
 * @property queryId queryId
 * @property hasMessage message is present
 * @property message message
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x0c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ChatPreviewPacket(
    val queryId: Int,
    val hasMessage: Boolean,
    val message: String?,
) : ClientBoundPacket {

    companion object : PacketSerializer<ChatPreviewPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ChatPreviewPacket {
            val queryId = input.readInt()
            val hasMessage = input.readBoolean()
            val message = if (hasMessage) input.readString() else null

            return ChatPreviewPacket(queryId, hasMessage, message)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ChatPreviewPacket) {
            output.writeInt(value.queryId)
            output.writeBoolean(value.hasMessage)
            if (value.hasMessage) output.writeString(value.message!!)
        }
    }
}