package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x0c | play | clientbound
 *
 * @property queryId queryId
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x0c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ChatPreviewPacket(
    val queryId: Int,
) : ClientBoundPacket {

    companion object : PacketSerializer<ChatPreviewPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ChatPreviewPacket {
            val queryId = input.readInt()

            return ChatPreviewPacket(queryId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ChatPreviewPacket) {
            output.writeInt(value.queryId)
        }
    }
}