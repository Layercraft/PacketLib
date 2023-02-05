package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Entity Metadata | 0x4e | play | clientbound
 *
 * @param entityId entityId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17964#Set_Entity_Metadata">https://wiki.vg/Protocol#Set_Entity_Metadata</a>
 */

@MinecraftPacket(id = 0x4e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityMetadataPacket(
    val entityId: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityMetadataPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EntityMetadataPacket {
            val entityId = input.readVarInt()

            return EntityMetadataPacket(entityId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityMetadataPacket) {
            output.writeVarInt(value.entityId)
        }
    }
}