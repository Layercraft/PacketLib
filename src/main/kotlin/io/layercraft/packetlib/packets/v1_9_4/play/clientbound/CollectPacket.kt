package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Collect Item | 0x48 | play | clientbound
 *
 * @property collectedEntityId collectedEntityId
 * @property collectorEntityId collectorEntityId
 * @see <a href="https://wiki.vg/Protocol#Collect_Item">https://wiki.vg/Protocol#Collect_Item</a>
 */

@MinecraftPacket(packetId = 0x48, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class CollectPacket(
    val collectedEntityId: Int, // varint
    val collectorEntityId: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<CollectPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): CollectPacket {
            val collectedEntityId = input.readVarInt()
            val collectorEntityId = input.readVarInt()

            return CollectPacket(collectedEntityId, collectorEntityId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: CollectPacket) {
            output.writeVarInt(value.collectedEntityId)
            output.writeVarInt(value.collectorEntityId)
        }
    }
}
