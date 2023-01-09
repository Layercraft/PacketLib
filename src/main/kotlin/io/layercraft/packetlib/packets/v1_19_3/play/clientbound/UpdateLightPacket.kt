package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Light | 0x23 | play | clientbound
 *
 * @param chunkX chunkX
 * @param chunkZ chunkZ
 * @param trustEdges trustEdges
 * @param skyLightMask skyLightMask
 * @param blockLightMask blockLightMask
 * @param emptySkyLightMask emptySkyLightMask
 * @param emptyBlockLightMask emptyBlockLightMask
 * @param skyLight skyLight
 * @param blockLight blockLight
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Update_Light">https://wiki.vg/Protocol#Update_Light</a>
 */

@MinecraftPacket(id = 0x23, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class UpdateLightPacket(
    val chunkX: Int, // varint
    val chunkZ: Int, // varint
    val trustEdges: Boolean,
    val skyLightMask: List<Long>, // varint array
    val blockLightMask: List<Long>, // varint array
    val emptySkyLightMask: List<Long>, // varint array
    val emptyBlockLightMask: List<Long>, // varint array
    val skyLight: List<List<UByte>>, // varint array
    val blockLight: List<List<UByte>>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<UpdateLightPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateLightPacket {
            val chunkX = input.readVarInt()
            val chunkZ = input.readVarInt()
            val trustEdges = input.readBoolean()
            val skyLightMask = input.readVarIntArray { arrayInput -> arrayInput.readLong() }
            val blockLightMask = input.readVarIntArray { arrayInput -> arrayInput.readLong() }
            val emptySkyLightMask = input.readVarIntArray { arrayInput -> arrayInput.readLong() }
            val emptyBlockLightMask = input.readVarIntArray { arrayInput -> arrayInput.readLong() }
            val skyLight = input.readVarIntArray { arrayInput1 -> arrayInput1.readVarIntArray { arrayInput -> arrayInput.readUByte() } }
            val blockLight = input.readVarIntArray { arrayInput1 -> arrayInput1.readVarIntArray { arrayInput -> arrayInput.readUByte() } }

            return UpdateLightPacket(chunkX, chunkZ, trustEdges, skyLightMask, blockLightMask, emptySkyLightMask, emptyBlockLightMask, skyLight, blockLight)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateLightPacket) {
            output.writeVarInt(value.chunkX)
            output.writeVarInt(value.chunkZ)
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