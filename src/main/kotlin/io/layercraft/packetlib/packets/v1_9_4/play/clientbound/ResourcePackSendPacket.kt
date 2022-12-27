package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Resource Pack Send | 0x32 | play | clientbound
 *
 * @property url url
 * @property hash hash
 * @see <a href="https://wiki.vg/Protocol#Resource_Pack_Send">https://wiki.vg/Protocol#Resource_Pack_Send</a>
 */

@MinecraftPacket(id = 0x32, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ResourcePackSendPacket(
    val url: String,
    val hash: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<ResourcePackSendPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ResourcePackSendPacket {
            val url = input.readString()
            val hash = input.readString()

            return ResourcePackSendPacket(url, hash)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ResourcePackSendPacket) {
            output.writeString(value.url)
            output.writeString(value.hash)
        }
    }
}
