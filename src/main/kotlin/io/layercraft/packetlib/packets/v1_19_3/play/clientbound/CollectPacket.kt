package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Pickup Item | 0x63 | play | clientbound
 *
 * @param collectedEntityId collectedEntityId
 * @param collectorEntityId collectorEntityId
 * @param pickupItemCount pickupItemCount
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Pickup_Item">https://wiki.vg/Protocol#Pickup_Item</a>
 */

data class CollectPacket(
    val collectedEntityId: Int, // varint
    val collectorEntityId: Int, // varint
    val pickupItemCount: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<CollectPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): CollectPacket {
            val collectedEntityId = input.readVarInt()
            val collectorEntityId = input.readVarInt()
            val pickupItemCount = input.readVarInt()

            return CollectPacket(collectedEntityId, collectorEntityId, pickupItemCount)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: CollectPacket) {
            output.writeVarInt(value.collectedEntityId)
            output.writeVarInt(value.collectorEntityId)
            output.writeVarInt(value.pickupItemCount)
        }
    }
}