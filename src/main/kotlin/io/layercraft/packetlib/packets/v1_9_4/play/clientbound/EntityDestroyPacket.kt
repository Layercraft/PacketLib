package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Destroy Entities | 0x30 | play | clientbound
 *

 * @see <a href="https://wiki.vg/Protocol#Destroy_Entities">https://wiki.vg/Protocol#Destroy_Entities</a>
 */

@MinecraftPacket(packetId = 0x30, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
 class EntityDestroyPacket(

) : ClientBoundPacket {

    companion object : PacketSerializer<EntityDestroyPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityDestroyPacket {

            return EntityDestroyPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityDestroyPacket) {

        }
    }
}
