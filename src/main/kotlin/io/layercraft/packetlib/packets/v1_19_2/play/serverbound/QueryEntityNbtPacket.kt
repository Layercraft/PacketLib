package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Query Entity Tag | 0x0f | play | serverbound
 *
 * @property transactionId transactionId
 * @property entityId entityId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Query_Entity_Tag">https://wiki.vg/Protocol#Query_Entity_Tag</a>
 */

@MinecraftPacket(id = 0x0f, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class QueryEntityNbtPacket(
    val transactionId: Int, // varint
    val entityId: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<QueryEntityNbtPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): QueryEntityNbtPacket {
            val transactionId = input.readVarInt()
            val entityId = input.readVarInt()

            return QueryEntityNbtPacket(transactionId, entityId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: QueryEntityNbtPacket) {
            output.writeVarInt(value.transactionId)
            output.writeVarInt(value.entityId)
        }
    }
}
