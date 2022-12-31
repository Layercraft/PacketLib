package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position
/**
 * Block Update | 0x09 | play | clientbound
 *
 * @property location location
 * @property type type
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Block_Update">https://wiki.vg/Protocol#Block_Update</a>
 */

@MinecraftPacket(id = 0x09, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class BlockChangePacket(
    val location: Position,
    val type: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<BlockChangePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): BlockChangePacket {
            val location = input.readPosition()
            val type = input.readVarInt()

            return BlockChangePacket(location, type)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: BlockChangePacket) {
            output.writePosition(value.location)
            output.writeVarInt(value.type)
        }
    }
}
