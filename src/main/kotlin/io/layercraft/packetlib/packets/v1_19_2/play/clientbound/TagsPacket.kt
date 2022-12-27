package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x6b | play | clientbound
 *

 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x6b, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
class TagsPacket() : ClientBoundPacket {

    companion object : PacketSerializer<TagsPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): TagsPacket {
            return TagsPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: TagsPacket) {
        }
    }
}