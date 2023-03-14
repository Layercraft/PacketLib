package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Remove Entities | 0x3a | play | clientbound
 *
 * @param entityIds entityIds
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Remove_Entities">https://wiki.vg/Protocol#Remove_Entities</a>
 */

@MinecraftPacket(id = 0x3a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityDestroyPacket(
    val entityIds: List<Int>, // varint array of varint
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityDestroyPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EntityDestroyPacket {
            val entityIds = input.readVarIntArray { arrayInput -> arrayInput.readVarInt() }

            return EntityDestroyPacket(entityIds)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityDestroyPacket) {
            output.writeVarIntArray(value.entityIds) { arrayValue, arrayOutput -> arrayOutput.writeVarInt(arrayValue) }
        }
    }
}