package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x0f | play | clientbound
 *
 * @property message message
 * @property position position
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x0f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ChatPacket(
    val message: String,
    val position: Byte,
) : ClientBoundPacket {

    companion object : PacketSerializer<ChatPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ChatPacket {
            val message = input.readString()
            val position = input.readByte()

            return ChatPacket(message, position)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ChatPacket) {
            output.writeString(value.message)
            output.writeByte(value.position)
        }
    }
}
