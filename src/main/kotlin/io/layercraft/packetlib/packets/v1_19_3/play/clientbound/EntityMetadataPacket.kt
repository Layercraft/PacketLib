package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Entity Metadata | 0x4e | play | clientbound
 *
 * @param entityId entityId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Entity_Metadata">https://wiki.vg/Protocol#Set_Entity_Metadata</a>
 */

data class EntityMetadataPacket(
    val entityId: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityMetadataPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): EntityMetadataPacket {
            val entityId = input.readVarInt()

            return EntityMetadataPacket(entityId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: EntityMetadataPacket) {
            output.writeVarInt(value.entityId)
        }
    }
}