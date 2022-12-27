package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * System Chat Message | 0x62 | play | clientbound
 *
 * @property content content
 * @property isActionBar isActionBar
 * @see <a href="https://wiki.vg/Protocol#System_Chat_Message">https://wiki.vg/Protocol#System_Chat_Message</a>
 */

@MinecraftPacket(id = 0x62, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SystemChatPacket(
    val content: String,
    val isActionBar: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<SystemChatPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SystemChatPacket {
            val content = input.readString()
            val isActionBar = input.readBoolean()

            return SystemChatPacket(content, isActionBar)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SystemChatPacket) {
            output.writeString(value.content)
            output.writeBoolean(value.isActionBar)
        }
    }
}