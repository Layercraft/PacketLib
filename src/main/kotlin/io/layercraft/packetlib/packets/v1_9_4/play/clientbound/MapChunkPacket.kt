package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Chunk Data | 0x20 | play | clientbound
 *
 * @property x x
 * @property z z
 * @property groundUp groundUp
 * @property bitMap bitMap
 * @see <a href="https://wiki.vg/Protocol#Chunk_Data">https://wiki.vg/Protocol#Chunk_Data</a>
 */

@MinecraftPacket(id = 0x20, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class MapChunkPacket(
    val x: Int,
    val z: Int,
    val groundUp: Boolean,
    val bitMap: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<MapChunkPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): MapChunkPacket {
            val x = input.readInt()
            val z = input.readInt()
            val groundUp = input.readBoolean()
            val bitMap = input.readVarInt()

            return MapChunkPacket(x, z, groundUp, bitMap)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: MapChunkPacket) {
            output.writeInt(value.x)
            output.writeInt(value.z)
            output.writeBoolean(value.groundUp)
            output.writeVarInt(value.bitMap)
        }
    }
}
