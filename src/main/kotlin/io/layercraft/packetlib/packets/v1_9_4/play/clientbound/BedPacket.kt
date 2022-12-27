package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position

/**
 * Encryption Response | 0x2f | play | clientbound
 *
 * @property entityId entityId
 * @property location location
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x2f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class BedPacket(
    val entityId: Int, // varint
    val location: Position,
) : ClientBoundPacket {

    companion object : PacketSerializer<BedPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): BedPacket {
            val entityId = input.readVarInt()
            val location = input.readPosition()

            return BedPacket(entityId, location)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: BedPacket) {
            output.writeVarInt(value.entityId)
            output.writePosition(value.location)
        }
    }
}
