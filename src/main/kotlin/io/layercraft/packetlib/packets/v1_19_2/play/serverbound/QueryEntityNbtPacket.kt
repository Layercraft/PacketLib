package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x0f | play | serverbound
 *
 * @property transactionId transactionId
 * @property entityId entityId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x0f, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class QueryEntityNbtPacket(
    val transactionId: Int, // varint
    val entityId: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<QueryEntityNbtPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): QueryEntityNbtPacket {
            val transactionId = input.readVarInt()
            val entityId = input.readVarInt()

            return QueryEntityNbtPacket(transactionId, entityId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: QueryEntityNbtPacket) {
            output.writeVarInt(value.transactionId)
            output.writeVarInt(value.entityId)
        }
    }
}