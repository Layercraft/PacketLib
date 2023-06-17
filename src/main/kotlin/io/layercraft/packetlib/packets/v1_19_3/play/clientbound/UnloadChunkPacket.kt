package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Unload Chunk | 0x1b | play | clientbound
 *
 * @param chunkX chunkX
 * @param chunkZ chunkZ
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Unload_Chunk">https://wiki.vg/Protocol#Unload_Chunk</a>
 */

data class UnloadChunkPacket(
    val chunkX: Int,
    val chunkZ: Int,
) : ClientBoundPacket {
    companion object : PacketSerializer<UnloadChunkPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): UnloadChunkPacket {
            val chunkX = input.readInt()
            val chunkZ = input.readInt()

            return UnloadChunkPacket(chunkX, chunkZ)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: UnloadChunkPacket) {
            output.writeInt(value.chunkX)
            output.writeInt(value.chunkZ)
        }
    }
}