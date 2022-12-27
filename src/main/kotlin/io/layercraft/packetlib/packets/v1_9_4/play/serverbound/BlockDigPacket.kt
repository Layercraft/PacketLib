package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position

/**
 * Player Digging | 0x13 | play | serverbound
 *
 * @property status status
 * @property location location
 * @property face face
 * @see <a href="https://wiki.vg/Protocol#Player_Digging">https://wiki.vg/Protocol#Player_Digging</a>
 */

@MinecraftPacket(id = 0x13, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class BlockDigPacket(
    val status: Int, // varint
    val location: Position,
    val face: Byte,
) : ServerBoundPacket {

    companion object : PacketSerializer<BlockDigPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): BlockDigPacket {
            val status = input.readVarInt()
            val location = input.readPosition()
            val face = input.readByte()

            return BlockDigPacket(status, location, face)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: BlockDigPacket) {
            output.writeVarInt(value.status)
            output.writePosition(value.location)
            output.writeByte(value.face)
        }
    }
}
