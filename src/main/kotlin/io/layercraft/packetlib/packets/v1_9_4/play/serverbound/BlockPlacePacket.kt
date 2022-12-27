package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position 

/**
 * Encryption Response | 0x1c | play | serverbound
 *
 * @property location location
 * @property direction direction
 * @property hand hand
 * @property cursorX cursorX
 * @property cursorY cursorY
 * @property cursorZ cursorZ
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x1c, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class BlockPlacePacket(
    val location: Position,
    val direction: Int, // varint
    val hand: Int, // varint
    val cursorX: Byte,
    val cursorY: Byte,
    val cursorZ: Byte,
) : ServerBoundPacket {

    companion object : PacketSerializer<BlockPlacePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): BlockPlacePacket {
            val location = input.readPosition()
            val direction = input.readVarInt()
            val hand = input.readVarInt()
            val cursorX = input.readByte()
            val cursorY = input.readByte()
            val cursorZ = input.readByte()

            return BlockPlacePacket(location, direction, hand, cursorX, cursorY, cursorZ)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: BlockPlacePacket) {
            output.writePosition(value.location)
            output.writeVarInt(value.direction)
            output.writeVarInt(value.hand)
            output.writeByte(value.cursorX)
            output.writeByte(value.cursorY)
            output.writeByte(value.cursorZ)
        }
    }
}
