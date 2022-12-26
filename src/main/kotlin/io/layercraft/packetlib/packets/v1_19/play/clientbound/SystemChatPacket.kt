package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x5f | play | clientbound
 *
 * @property content content
 * @property type type
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x5f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SystemChatPacket(
    val content: String,
    val type: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<SystemChatPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SystemChatPacket {
            val content = input.readString()
            val type = input.readVarInt()

            return SystemChatPacket(content, type)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SystemChatPacket) {
            output.writeString(value.content)
            output.writeVarInt(value.type)
        }
    }
}