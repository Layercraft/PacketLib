package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position
/**
 * Player Action | 0x1c | play | serverbound
 *
 * @param status status
 * @param location location
 * @param face face
 * @param sequence sequence
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Player_Action">https://wiki.vg/Protocol#Player_Action</a>
 */

@MinecraftPacket(id = 0x1c, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class BlockDigPacket(
    val status: Int, // varint
    val location: Position,
    val face: Byte,
    val sequence: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<BlockDigPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): BlockDigPacket {
            val status = input.readVarInt()
            val location = input.readPosition()
            val face = input.readByte()
            val sequence = input.readVarInt()

            return BlockDigPacket(status, location, face, sequence)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: BlockDigPacket) {
            output.writeVarInt(value.status)
            output.writePosition(value.location)
            output.writeByte(value.face)
            output.writeVarInt(value.sequence)
        }
    }
}