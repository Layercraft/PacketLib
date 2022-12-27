package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x1b | play | clientbound
 *
 * @property entityId entityId
 * @property entityStatus entityStatus
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x1b, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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
