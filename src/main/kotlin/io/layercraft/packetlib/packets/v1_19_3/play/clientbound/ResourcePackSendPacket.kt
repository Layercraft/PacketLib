package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Resource Pack | 0x3c | play | clientbound
 *
 * @param url url
 * @param hash hash
 * @param forced forced
 * @param hasPromptMessage promptMessage is present
 * @param promptMessage promptMessage
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Resource_Pack">https://wiki.vg/Protocol#Resource_Pack</a>
 */

data class ResourcePackSendPacket(
    val url: String,
    val hash: String,
    val forced: Boolean,
    val hasPromptMessage: Boolean,
    val promptMessage: String?,
) : ClientBoundPacket {
    companion object : PacketSerializer<ResourcePackSendPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): ResourcePackSendPacket {
            val url = input.readString()
            val hash = input.readString()
            val forced = input.readBoolean()
            val hasPromptMessage = input.readBoolean()
            val promptMessage = if (hasPromptMessage) input.readString() else null

            return ResourcePackSendPacket(url, hash, forced, hasPromptMessage, promptMessage)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: ResourcePackSendPacket) {
            output.writeString(value.url)
            output.writeString(value.hash)
            output.writeBoolean(value.forced)
            output.writeBoolean(value.hasPromptMessage)
            if (value.hasPromptMessage) output.writeString(value.promptMessage!!)
        }
    }
}