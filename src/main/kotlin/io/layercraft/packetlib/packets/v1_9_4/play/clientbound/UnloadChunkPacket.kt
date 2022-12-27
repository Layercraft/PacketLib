package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x1d | play | clientbound
 *
 * @property chunkX chunkX
 * @property chunkZ chunkZ
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x1d, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class UnloadChunkPacket(
    val chunkX: Int,
    val chunkZ: Int,
) : ClientBoundPacket {

    companion object : PacketSerializer<UnloadChunkPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): UnloadChunkPacket {
            val chunkX = input.readInt()
            val chunkZ = input.readInt()

            return UnloadChunkPacket(chunkX, chunkZ)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: UnloadChunkPacket) {
            output.writeInt(value.chunkX)
            output.writeInt(value.chunkZ)
        }
    }
}
