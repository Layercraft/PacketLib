package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Entity Event | 0x1a | play | clientbound
 *
 * @param entityId entityId
 * @param entityStatus entityStatus
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Entity_Event">https://wiki.vg/Protocol#Entity_Event</a>
 */

@MinecraftPacket(id = 0x1a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityStatusPacket(
    val entityId: Int,
    val entityStatus: Byte,
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityStatusPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EntityStatusPacket {
            val entityId = input.readInt()
            val entityStatus = input.readByte()

            return EntityStatusPacket(entityId, entityStatus)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityStatusPacket) {
            output.writeInt(value.entityId)
            output.writeByte(value.entityStatus)
        }
    }
}