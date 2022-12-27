package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x4a | play | clientbound
 *
 * @property entityId entityId
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x4a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityUpdateAttributesPacket(
    val entityId: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<EntityUpdateAttributesPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityUpdateAttributesPacket {
            val entityId = input.readVarInt()

            return EntityUpdateAttributesPacket(entityId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityUpdateAttributesPacket) {
            output.writeVarInt(value.entityId)
        }
    }
}
