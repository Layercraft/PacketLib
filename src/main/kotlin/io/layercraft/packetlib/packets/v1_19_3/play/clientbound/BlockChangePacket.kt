package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import io.layercraft.packetlib.types.Position
/**
 * Block Update | 0x09 | play | clientbound
 *
 * @param location location
 * @param type type
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Block_Update">https://wiki.vg/Protocol#Block_Update</a>
 */

data class BlockChangePacket(
    val location: Position,
    val type: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<BlockChangePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): BlockChangePacket {
            val location = input.readPosition()
            val type = input.readVarInt()

            return BlockChangePacket(location, type)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: BlockChangePacket) {
            output.writePosition(value.location)
            output.writeVarInt(value.type)
        }
    }
}