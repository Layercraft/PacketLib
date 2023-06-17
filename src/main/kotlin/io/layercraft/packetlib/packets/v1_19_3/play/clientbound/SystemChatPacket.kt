package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * System Chat Message | 0x60 | play | clientbound
 *
 * @param content content
 * @param isActionBar isActionBar
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#System_Chat_Message">https://wiki.vg/Protocol#System_Chat_Message</a>
 */

data class SystemChatPacket(
    val content: String,
    val isActionBar: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<SystemChatPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SystemChatPacket {
            val content = input.readString()
            val isActionBar = input.readBoolean()

            return SystemChatPacket(content, isActionBar)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SystemChatPacket) {
            output.writeString(value.content)
            output.writeBoolean(value.isActionBar)
        }
    }
}