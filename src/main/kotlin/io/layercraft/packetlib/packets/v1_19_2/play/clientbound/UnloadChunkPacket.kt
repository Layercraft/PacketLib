package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x1c | play | clientbound
 *
 * @property chunkX chunkX
 * @property chunkZ chunkZ
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x1c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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