package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Equipment | 0x51 | play | clientbound
 *
 * @param entityId entityId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Equipment">https://wiki.vg/Protocol#Set_Equipment</a>
 */

data class EntityEquipmentPacket(
    val entityId: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityEquipmentPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): EntityEquipmentPacket {
            val entityId = input.readVarInt()

            return EntityEquipmentPacket(entityId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: EntityEquipmentPacket) {
            output.writeVarInt(value.entityId)
        }
    }
}