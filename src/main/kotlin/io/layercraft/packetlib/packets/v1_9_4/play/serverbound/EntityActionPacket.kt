package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Entity Action | 0x14 | play | serverbound
 *
 * @property entityId entityId
 * @property actionId actionId
 * @property jumpBoost jumpBoost
 * @see <a href="https://wiki.vg/Protocol#Entity_Action">https://wiki.vg/Protocol#Entity_Action</a>
 */

@MinecraftPacket(id = 0x14, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class EntityActionPacket(
    val entityId: Int, // varint
    val actionId: Int, // varint
    val jumpBoost: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<EntityActionPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityActionPacket {
            val entityId = input.readVarInt()
            val actionId = input.readVarInt()
            val jumpBoost = input.readVarInt()

            return EntityActionPacket(entityId, actionId, jumpBoost)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityActionPacket) {
            output.writeVarInt(value.entityId)
            output.writeVarInt(value.actionId)
            output.writeVarInt(value.jumpBoost)
        }
    }
}
