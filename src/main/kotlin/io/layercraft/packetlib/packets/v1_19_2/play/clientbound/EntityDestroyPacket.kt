package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x3b | play | clientbound
 *

 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x3b, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
class EntityDestroyPacket() : ClientBoundPacket {

    companion object : PacketSerializer<EntityDestroyPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityDestroyPacket {
            return EntityDestroyPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityDestroyPacket) {
        }
    }
}