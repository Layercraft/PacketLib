package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Entity Event | 0x19 | play | clientbound
 *
 * @param entityId entityId
 * @param entityStatus entityStatus
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Entity_Event">https://wiki.vg/Protocol#Entity_Event</a>
 */

data class EntityStatusPacket(
    val entityId: Int,
    val entityStatus: Byte,
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityStatusPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): EntityStatusPacket {
            val entityId = input.readInt()
            val entityStatus = input.readByte()

            return EntityStatusPacket(entityId, entityStatus)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: EntityStatusPacket) {
            output.writeInt(value.entityId)
            output.writeByte(value.entityStatus)
        }
    }
}