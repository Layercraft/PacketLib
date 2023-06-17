package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Player Command | 0x1d | play | serverbound
 *
 * @param entityId entityId
 * @param actionId actionId
 * @param jumpBoost jumpBoost
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Player_Command">https://wiki.vg/Protocol#Player_Command</a>
 */

data class EntityActionPacket(
    val entityId: Int, // varint
    val actionId: Int, // varint
    val jumpBoost: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<EntityActionPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): EntityActionPacket {
            val entityId = input.readVarInt()
            val actionId = input.readVarInt()
            val jumpBoost = input.readVarInt()

            return EntityActionPacket(entityId, actionId, jumpBoost)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: EntityActionPacket) {
            output.writeVarInt(value.entityId)
            output.writeVarInt(value.actionId)
            output.writeVarInt(value.jumpBoost)
        }
    }
}