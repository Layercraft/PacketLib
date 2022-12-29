package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Chunk Data and Update Light | 0x21 | play | clientbound
 *
 * @property x x
 * @property z z
 * @property heightmaps heightmaps
 * @property chunkData chunkData
 * @property trustEdges trustEdges
 * @property skyLightMask skyLightMask
 * @property blockLightMask blockLightMask
 * @property emptySkyLightMask emptySkyLightMask
 * @property emptyBlockLightMask emptyBlockLightMask
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Chunk_Data_and_Update_Light">https://wiki.vg/Protocol#Chunk_Data_and_Update_Light</a>
 */

@MinecraftPacket(id = 0x21, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class MapChunkPacket(
    val x: Int,
    val z: Int,
    val heightmaps: ByteArray,
    val chunkData: ByteArray,
    val trustEdges: Boolean,
    val skyLightMask: List<Long>, // varint array
    val blockLightMask: List<Long>, // varint array
    val emptySkyLightMask: List<Long>, // varint array
    val emptyBlockLightMask: List<Long>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<MapChunkPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): MapChunkPacket {
            val x = input.readInt()
            val z = input.readInt()
            val heightmaps = input.readNBT()
            val chunkData = input.readVarIntByteArray()
            val trustEdges = input.readBoolean()
            val skyLightMask = input.readVarIntArray { arrayInput -> arrayInput.readLong() }
            val blockLightMask = input.readVarIntArray { arrayInput -> arrayInput.readLong() }
            val emptySkyLightMask = input.readVarIntArray { arrayInput -> arrayInput.readLong() }
            val emptyBlockLightMask = input.readVarIntArray { arrayInput -> arrayInput.readLong() }

            return MapChunkPacket(x, z, heightmaps, chunkData, trustEdges, skyLightMask, blockLightMask, emptySkyLightMask, emptyBlockLightMask)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: MapChunkPacket) {
            output.writeInt(value.x)
            output.writeInt(value.z)
            output.writeBytes(value.heightmaps)
            output.writeVarIntByteArray(value.chunkData)
            output.writeBoolean(value.trustEdges)
            output.writeVarIntArray(value.skyLightMask) { arrayValue, arrayOutput -> arrayOutput.writeLong(arrayValue) }
            output.writeVarIntArray(value.blockLightMask) { arrayValue, arrayOutput -> arrayOutput.writeLong(arrayValue) }
            output.writeVarIntArray(value.emptySkyLightMask) { arrayValue, arrayOutput -> arrayOutput.writeLong(arrayValue) }
            output.writeVarIntArray(value.emptyBlockLightMask) { arrayValue, arrayOutput -> arrayOutput.writeLong(arrayValue) }
        }
    }
}