package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Equipment | 0x53 | play | clientbound
 *
 * @property entityId entityId
 * @see <a href="https://wiki.vg/Protocol#Set_Equipment">https://wiki.vg/Protocol#Set_Equipment</a>
 */

@MinecraftPacket(packetId = 0x53, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityEquipmentPacket(
    val entityId: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<EntityEquipmentPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityEquipmentPacket {
            val entityId = input.readVarInt()

            return EntityEquipmentPacket(entityId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityEquipmentPacket) {
            output.writeVarInt(value.entityId)
        }
    }
}