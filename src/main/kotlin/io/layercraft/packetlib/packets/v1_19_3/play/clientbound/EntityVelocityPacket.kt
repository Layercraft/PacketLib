package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Entity Velocity | 0x50 | play | clientbound
 *
 * @param entityId entityId
 * @param velocityX velocityX
 * @param velocityY velocityY
 * @param velocityZ velocityZ
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Entity_Velocity">https://wiki.vg/Protocol#Set_Entity_Velocity</a>
 */

data class EntityVelocityPacket(
    val entityId: Int, // varint
    val velocityX: Short,
    val velocityY: Short,
    val velocityZ: Short,
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityVelocityPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): EntityVelocityPacket {
            val entityId = input.readVarInt()
            val velocityX = input.readShort()
            val velocityY = input.readShort()
            val velocityZ = input.readShort()

            return EntityVelocityPacket(entityId, velocityX, velocityY, velocityZ)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: EntityVelocityPacket) {
            output.writeVarInt(value.entityId)
            output.writeShort(value.velocityX)
            output.writeShort(value.velocityY)
            output.writeShort(value.velocityZ)
        }
    }
}