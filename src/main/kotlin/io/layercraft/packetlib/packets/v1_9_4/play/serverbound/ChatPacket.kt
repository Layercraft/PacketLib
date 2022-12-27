package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 *  | 0x02 | play | serverbound
 *
 * @property message message
 * @see <a href="https://wiki.vg/Protocol#Chat_Message_.28serverbound.29">https://wiki.vg/Protocol#Chat_Message_.28serverbound.29</a>
 */

@MinecraftPacket(id = 0x02, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class ChatPacket(
    val message: String,
) : ServerBoundPacket {

    companion object : PacketSerializer<ChatPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ChatPacket {
            val message = input.readString()

            return ChatPacket(message)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ChatPacket) {
            output.writeString(value.message)
        }
    }
}
