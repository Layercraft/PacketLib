package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Remove Entities | 0x3a | play | clientbound
 *
 * @param entityIds entityIds
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Remove_Entities">https://wiki.vg/Protocol#Remove_Entities</a>
 */

data class EntityDestroyPacket(
    val entityIds: List<Int>, // varint array of varint
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityDestroyPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): EntityDestroyPacket {
            val entityIds = input.readVarIntArray { arrayInput -> arrayInput.readVarInt() }

            return EntityDestroyPacket(entityIds)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: EntityDestroyPacket) {
            output.writeVarIntArray(value.entityIds) { arrayValue, arrayOutput -> arrayOutput.writeVarInt(arrayValue) }
        }
    }
}