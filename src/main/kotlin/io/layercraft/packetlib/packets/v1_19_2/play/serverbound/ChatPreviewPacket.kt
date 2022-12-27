package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 *  | 0x06 | play | serverbound
 *
 * @property query query
 * @property message message
 * @see <a href="https://wiki.vg/Protocol#Chat_Preview_.28serverbound.29">https://wiki.vg/Protocol#Chat_Preview_.28serverbound.29</a>
 */

@MinecraftPacket(id = 0x06, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class ChatPreviewPacket(
    val query: Int,
    val message: String,
) : ServerBoundPacket {

    companion object : PacketSerializer<ChatPreviewPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ChatPreviewPacket {
            val query = input.readInt()
            val message = input.readString()

            return ChatPreviewPacket(query, message)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ChatPreviewPacket) {
            output.writeInt(value.query)
            output.writeString(value.message)
        }
    }
}