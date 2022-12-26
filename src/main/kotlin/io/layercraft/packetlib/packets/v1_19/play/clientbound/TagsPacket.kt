package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Tags | 0x68 | play | clientbound
 *

 * @see <a href="https://wiki.vg/Protocol#Update_Tags">https://wiki.vg/Protocol#Update_Tags</a>
 */

@MinecraftPacket(packetId = 0x68, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
class TagsPacket() : ClientBoundPacket {

    companion object : PacketSerializer<TagsPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): TagsPacket {
            return TagsPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: TagsPacket) {
        }
    }
}