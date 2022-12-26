package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Pickup Item | 0x62 | play | clientbound
 *
 * @property collectedEntityId collectedEntityId
 * @property collectorEntityId collectorEntityId
 * @property pickupItemCount pickupItemCount
 * @see <a href="https://wiki.vg/Protocol#Pickup_Item">https://wiki.vg/Protocol#Pickup_Item</a>
 */

@MinecraftPacket(packetId = 0x62, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class CollectPacket(
    val collectedEntityId: Int, // varint
    val collectorEntityId: Int, // varint
    val pickupItemCount: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<CollectPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): CollectPacket {
            val collectedEntityId = input.readVarInt()
            val collectorEntityId = input.readVarInt()
            val pickupItemCount = input.readVarInt()

            return CollectPacket(collectedEntityId, collectorEntityId, pickupItemCount)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: CollectPacket) {
            output.writeVarInt(value.collectedEntityId)
            output.writeVarInt(value.collectorEntityId)
            output.writeVarInt(value.pickupItemCount)
        }
    }
}