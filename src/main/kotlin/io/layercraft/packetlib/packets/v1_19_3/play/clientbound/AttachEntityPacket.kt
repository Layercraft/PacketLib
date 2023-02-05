package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Link Entities | 0x4f | play | clientbound
 *
 * @param entityId entityId
 * @param vehicleId vehicleId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17964#Link_Entities">https://wiki.vg/Protocol#Link_Entities</a>
 */

@MinecraftPacket(id = 0x4f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class AttachEntityPacket(
    val entityId: Int,
    val vehicleId: Int,
) : ClientBoundPacket {
    companion object : PacketSerializer<AttachEntityPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): AttachEntityPacket {
            val entityId = input.readInt()
            val vehicleId = input.readInt()

            return AttachEntityPacket(entityId, vehicleId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: AttachEntityPacket) {
            output.writeInt(value.entityId)
            output.writeInt(value.vehicleId)
        }
    }
}