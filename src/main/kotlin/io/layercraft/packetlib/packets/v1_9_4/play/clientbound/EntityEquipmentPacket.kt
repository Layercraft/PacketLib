package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x3c | play | clientbound
 *
 * @property entityId entityId
 * @property slot slot
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x3c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityEquipmentPacket(
    val entityId: Int, // varint
    val slot: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<EntityEquipmentPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityEquipmentPacket {
            val entityId = input.readVarInt()
            val slot = input.readVarInt()

            return EntityEquipmentPacket(entityId, slot)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityEquipmentPacket) {
            output.writeVarInt(value.entityId)
            output.writeVarInt(value.slot)
        }
    }
}
