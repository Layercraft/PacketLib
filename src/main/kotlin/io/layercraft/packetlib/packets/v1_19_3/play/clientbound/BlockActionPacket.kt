package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import io.layercraft.packetlib.types.Position
/**
 * Block Action | 0x08 | play | clientbound
 *
 * @param location location
 * @param byte1 byte1
 * @param byte2 byte2
 * @param blockId blockId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Block_Action">https://wiki.vg/Protocol#Block_Action</a>
 */

data class BlockActionPacket(
    val location: Position,
    val byte1: UByte,
    val byte2: UByte,
    val blockId: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<BlockActionPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): BlockActionPacket {
            val location = input.readPosition()
            val byte1 = input.readUByte()
            val byte2 = input.readUByte()
            val blockId = input.readVarInt()

            return BlockActionPacket(location, byte1, byte2, blockId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: BlockActionPacket) {
            output.writePosition(value.location)
            output.writeUByte(value.byte1)
            output.writeUByte(value.byte2)
            output.writeVarInt(value.blockId)
        }
    }
}