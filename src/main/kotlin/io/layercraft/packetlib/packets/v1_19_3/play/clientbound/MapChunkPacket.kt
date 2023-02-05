package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.ChunkBlockEntity
import io.layercraft.packetlib.types.NBT
/**
 * Chunk Data and Update Light | 0x20 | play | clientbound
 *
 * @param x x
 * @param z z
 * @param heightmaps heightmaps
 * @param chunkData chunkData
 * @param blockEntities blockEntities
 * @param trustEdges trustEdges
 * @param skyLightMask skyLightMask
 * @param blockLightMask blockLightMask
 * @param emptySkyLightMask emptySkyLightMask
 * @param emptyBlockLightMask emptyBlockLightMask
 * @param skyLight skyLight
 * @param blockLight blockLight
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17964#Chunk_Data_and_Update_Light">https://wiki.vg/Protocol#Chunk_Data_and_Update_Light</a>
 */

@MinecraftPacket(id = 0x20, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class MapChunkPacket(
    val x: Int,
    val z: Int,
    val heightmaps: NBT,
    val chunkData: ByteArray,
    val blockEntities: List<ChunkBlockEntity>, // varint array
    val trustEdges: Boolean,
    val skyLightMask: List<Long>, // varint array
    val blockLightMask: List<Long>, // varint array
    val emptySkyLightMask: List<Long>, // varint array
    val emptyBlockLightMask: List<Long>, // varint array
    val skyLight: List<List<UByte>>, // varint array
    val blockLight: List<List<UByte>>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<MapChunkPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): MapChunkPacket {
            val x = input.readInt()
            val z = input.readInt()
            val heightmaps = input.readNbt()
            val chunkData = input.readVarIntByteArray()
            val blockEntities = input.readVarIntArray { arrayInput -> arrayInput.readChunkBlockEntity() }
            val trustEdges = input.readBoolean()
            val skyLightMask = input.readVarIntArray { arrayInput -> arrayInput.readLong() }
            val blockLightMask = input.readVarIntArray { arrayInput -> arrayInput.readLong() }
            val emptySkyLightMask = input.readVarIntArray { arrayInput -> arrayInput.readLong() }
            val emptyBlockLightMask = input.readVarIntArray { arrayInput -> arrayInput.readLong() }
            val skyLight = input.readVarIntArray { arrayInput1 -> arrayInput1.readVarIntArray { arrayInput -> arrayInput.readUByte() } }
            val blockLight = input.readVarIntArray { arrayInput1 -> arrayInput1.readVarIntArray { arrayInput -> arrayInput.readUByte() } }

            return MapChunkPacket(x, z, heightmaps, chunkData, blockEntities, trustEdges, skyLightMask, blockLightMask, emptySkyLightMask, emptyBlockLightMask, skyLight, blockLight)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: MapChunkPacket) {
            output.writeInt(value.x)
            output.writeInt(value.z)
            output.writeNbt(value.heightmaps)
            output.writeVarIntByteArray(value.chunkData)
            output.writeVarIntArray(value.blockEntities) { arrayValue, arrayOutput -> arrayOutput.writeChunkBlockEntity(arrayValue) }
            output.writeBoolean(value.trustEdges)
            output.writeVarIntArray(value.skyLightMask) { arrayValue, arrayOutput -> arrayOutput.writeLong(arrayValue) }
            output.writeVarIntArray(value.blockLightMask) { arrayValue, arrayOutput -> arrayOutput.writeLong(arrayValue) }
            output.writeVarIntArray(value.emptySkyLightMask) { arrayValue, arrayOutput -> arrayOutput.writeLong(arrayValue) }
            output.writeVarIntArray(value.emptyBlockLightMask) { arrayValue, arrayOutput -> arrayOutput.writeLong(arrayValue) }
            output.writeVarIntArray(value.skyLight) { arrayValue1, arrayOutput1 -> arrayOutput1.writeVarIntArray(arrayValue1) { arrayValue, arrayOutput -> arrayOutput.writeUByte(arrayValue) } }
            output.writeVarIntArray(value.blockLight) { arrayValue1, arrayOutput1 -> arrayOutput1.writeVarIntArray(arrayValue1) { arrayValue, arrayOutput -> arrayOutput.writeUByte(arrayValue) } }
        }
    }
}