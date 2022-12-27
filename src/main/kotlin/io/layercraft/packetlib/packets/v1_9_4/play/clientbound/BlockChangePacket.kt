package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position 

/**
 * Encryption Response | 0x0b | play | clientbound
 *
 * @property location location
 * @property type type
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x0b, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class BlockChangePacket(
    val location: Position,
    val type: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<BlockChangePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): BlockChangePacket {
            val location = input.readPosition()
            val type = input.readVarInt()

            return BlockChangePacket(location, type)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: BlockChangePacket) {
            output.writePosition(value.location)
            output.writeVarInt(value.type)
        }
    }
}
