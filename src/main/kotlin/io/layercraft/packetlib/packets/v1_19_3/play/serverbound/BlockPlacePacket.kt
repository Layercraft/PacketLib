package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import io.layercraft.packetlib.types.Position
/**
 * Use Item On | 0x31 | play | serverbound
 *
 * @param hand hand
 * @param location location
 * @param direction direction
 * @param cursorX cursorX
 * @param cursorY cursorY
 * @param cursorZ cursorZ
 * @param insideBlock insideBlock
 * @param sequence sequence
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Use_Item_On">https://wiki.vg/Protocol#Use_Item_On</a>
 */

data class BlockPlacePacket(
    val hand: Int, // varint
    val location: Position,
    val direction: Int, // varint
    val cursorX: Float,
    val cursorY: Float,
    val cursorZ: Float,
    val insideBlock: Boolean,
    val sequence: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<BlockPlacePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): BlockPlacePacket {
            val hand = input.readVarInt()
            val location = input.readPosition()
            val direction = input.readVarInt()
            val cursorX = input.readFloat()
            val cursorY = input.readFloat()
            val cursorZ = input.readFloat()
            val insideBlock = input.readBoolean()
            val sequence = input.readVarInt()

            return BlockPlacePacket(hand, location, direction, cursorX, cursorY, cursorZ, insideBlock, sequence)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: BlockPlacePacket) {
            output.writeVarInt(value.hand)
            output.writePosition(value.location)
            output.writeVarInt(value.direction)
            output.writeFloat(value.cursorX)
            output.writeFloat(value.cursorY)
            output.writeFloat(value.cursorZ)
            output.writeBoolean(value.insideBlock)
            output.writeVarInt(value.sequence)
        }
    }
}