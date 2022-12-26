package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x3a | play | clientbound
 *
 * @property url url
 * @property hash hash
 * @property forced forced
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x3a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ResourcePackSendPacket(
    val url: String,
    val hash: String,
    val forced: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<ResourcePackSendPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ResourcePackSendPacket {
            val url = input.readString()
            val hash = input.readString()
            val forced = input.readBoolean()

            return ResourcePackSendPacket(url, hash, forced)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ResourcePackSendPacket) {
            output.writeString(value.url)
            output.writeString(value.hash)
            output.writeBoolean(value.forced)
        }
    }
}