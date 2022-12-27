package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Entity | 0x28 | play | clientbound
 *
 * @property entityId entityId
 * @see <a href="https://wiki.vg/Protocol#Entity">https://wiki.vg/Protocol#Entity</a>
 */

@MinecraftPacket(packetId = 0x28, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityPacket(
    val entityId: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<EntityPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityPacket {
            val entityId = input.readVarInt()

            return EntityPacket(entityId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityPacket) {
            output.writeVarInt(value.entityId)
        }
    }
}
