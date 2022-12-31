package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Light | 0x24 | play | clientbound
 *
 * @property chunkX chunkX
 * @property chunkZ chunkZ
 * @property trustEdges trustEdges
 * @property skyLightMask skyLightMask
 * @property blockLightMask blockLightMask
 * @property emptySkyLightMask emptySkyLightMask
 * @property emptyBlockLightMask emptyBlockLightMask
 * @property skyLight skyLight
 * @property blockLight blockLight
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Update_Light">https://wiki.vg/Protocol#Update_Light</a>
 */

@MinecraftPacket(id = 0x24, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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
            val skyLight = input.readVarIntArray { arrayInput -> arrayInput.readVarIntArray { arrayInput -> arrayInput.readUByte() } }
            val blockLight = input.readVarIntArray { arrayInput -> arrayInput.readVarIntArray { arrayInput -> arrayInput.readUByte() } }

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
            output.writeVarIntArray(value.skyLight) { arrayValue, arrayOutput -> arrayOutput.writeVarIntArray(arrayValue) { arrayValue, arrayOutput -> arrayOutput.writeUByte(arrayValue) } }
            output.writeVarIntArray(value.blockLight) { arrayValue, arrayOutput -> arrayOutput.writeVarIntArray(arrayValue) { arrayValue, arrayOutput -> arrayOutput.writeUByte(arrayValue) } }
        }
    }
}
