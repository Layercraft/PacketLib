package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.MinecraftPacket
import io.layercraft.packetlib.packets.PacketDirection
import io.layercraft.packetlib.packets.PacketSerializer
import io.layercraft.packetlib.packets.PacketState
import io.layercraft.packetlib.packets.ServerBoundPacket
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position

/**
 * Use Item On | 0x31 | play | serverbound
 *
 * @property hand hand
 * @property location location
 * @property bound direction
 * @property cursorX cursorX
 * @property cursorY cursorY
 * @property cursorZ cursorZ
 * @property insideBlock insideBlock
 * @property sequence sequence
 * @see <a href="https://wiki.vg/Protocol#Use_Item_On">https://wiki.vg/Protocol#Use_Item_On</a>
 */

@MinecraftPacket(id = 0x31, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
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
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): BlockPlacePacket {
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

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: BlockPlacePacket) {
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