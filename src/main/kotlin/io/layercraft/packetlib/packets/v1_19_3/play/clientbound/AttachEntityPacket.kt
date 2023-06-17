package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Link Entities | 0x4f | play | clientbound
 *
 * @param entityId entityId
 * @param vehicleId vehicleId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Link_Entities">https://wiki.vg/Protocol#Link_Entities</a>
 */

data class AttachEntityPacket(
    val entityId: Int,
    val vehicleId: Int,
) : ClientBoundPacket {
    companion object : PacketSerializer<AttachEntityPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): AttachEntityPacket {
            val entityId = input.readInt()
            val vehicleId = input.readInt()

            return AttachEntityPacket(entityId, vehicleId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: AttachEntityPacket) {
            output.writeInt(value.entityId)
            output.writeInt(value.vehicleId)
        }
    }
}