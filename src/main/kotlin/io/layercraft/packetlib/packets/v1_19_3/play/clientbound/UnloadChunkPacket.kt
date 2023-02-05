package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Unload Chunk | 0x1b | play | clientbound
 *
 * @param chunkX chunkX
 * @param chunkZ chunkZ
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17964#Unload_Chunk">https://wiki.vg/Protocol#Unload_Chunk</a>
 */

@MinecraftPacket(id = 0x1b, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class UnloadChunkPacket(
    val chunkX: Int,
    val chunkZ: Int,
) : ClientBoundPacket {
    companion object : PacketSerializer<UnloadChunkPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): UnloadChunkPacket {
            val chunkX = input.readInt()
            val chunkZ = input.readInt()

            return UnloadChunkPacket(chunkX, chunkZ)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: UnloadChunkPacket) {
            output.writeInt(value.chunkX)
            output.writeInt(value.chunkZ)
        }
    }
}