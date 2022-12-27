package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Remove Entities | 0x38 | play | clientbound
 *

 * @see <a href="https://wiki.vg/Protocol#Remove_Entities">https://wiki.vg/Protocol#Remove_Entities</a>
 */

@MinecraftPacket(id = 0x38, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
class EntityDestroyPacket() : ClientBoundPacket {

    companion object : PacketSerializer<EntityDestroyPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityDestroyPacket {
            return EntityDestroyPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityDestroyPacket) {
        }
    }
}