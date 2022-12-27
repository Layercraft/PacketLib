package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x4d | play | clientbound
 *
 * @property entityId entityId
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x4d, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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