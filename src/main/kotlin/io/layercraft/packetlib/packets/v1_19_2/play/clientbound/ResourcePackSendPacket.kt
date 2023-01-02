package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Resource Pack (clientbound) | 0x3d | play | clientbound
 *
 * @param url url
 * @param hash hash
 * @param forced forced
 * @param hasPromptMessage promptMessage is present
 * @param promptMessage promptMessage
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Resource_Pack_.28clientbound.29">https://wiki.vg/Protocol#Resource_Pack_.28clientbound.29</a>
 */

@MinecraftPacket(id = 0x3d, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ResourcePackSendPacket(
    val url: String,
    val hash: String,
    val forced: Boolean,
    val hasPromptMessage: Boolean,
    val promptMessage: String?,
) : ClientBoundPacket {
    companion object : PacketSerializer<ResourcePackSendPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ResourcePackSendPacket {
            val url = input.readString()
            val hash = input.readString()
            val forced = input.readBoolean()
            val hasPromptMessage = input.readBoolean()
            val promptMessage = if (hasPromptMessage) input.readString() else null

            return ResourcePackSendPacket(url, hash, forced, hasPromptMessage, promptMessage)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ResourcePackSendPacket) {
            output.writeString(value.url)
            output.writeString(value.hash)
            output.writeBoolean(value.forced)
            output.writeBoolean(value.hasPromptMessage)
            if (value.hasPromptMessage) output.writeString(value.promptMessage!!)
        }
    }
}