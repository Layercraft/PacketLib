package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Entity Metadata | 0x39 | play | clientbound
 *
 * @property entityId entityId
 * @see <a href="https://wiki.vg/Protocol#Entity_Metadata">https://wiki.vg/Protocol#Entity_Metadata</a>
 */

@MinecraftPacket(id = 0x39, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityMetadataPacket(
    val entityId: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<EntityMetadataPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityMetadataPacket {
            val entityId = input.readVarInt()

            return EntityMetadataPacket(entityId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityMetadataPacket) {
            output.writeVarInt(value.entityId)
        }
    }
}
