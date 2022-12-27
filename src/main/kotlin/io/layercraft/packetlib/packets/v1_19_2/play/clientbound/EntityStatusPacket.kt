package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x1a | play | clientbound
 *
 * @property entityId entityId
 * @property entityStatus entityStatus
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x1a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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