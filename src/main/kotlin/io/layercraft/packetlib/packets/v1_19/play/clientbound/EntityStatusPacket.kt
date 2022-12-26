package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Entity Event | 0x18 | play | clientbound
 *
 * @property entityId entityId
 * @property entityStatus entityStatus
 * @see <a href="https://wiki.vg/Protocol#Entity_Event">https://wiki.vg/Protocol#Entity_Event</a>
 */

@MinecraftPacket(packetId = 0x18, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityStatusPacket(
    val entityId: Int,
    val entityStatus: Byte,
) : ClientBoundPacket {

    companion object : PacketSerializer<EntityStatusPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityStatusPacket {
            val entityId = input.readInt()
            val entityStatus = input.readByte()

            return EntityStatusPacket(entityId, entityStatus)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityStatusPacket) {
            output.writeInt(value.entityId)
            output.writeByte(value.entityStatus)
        }
    }
}