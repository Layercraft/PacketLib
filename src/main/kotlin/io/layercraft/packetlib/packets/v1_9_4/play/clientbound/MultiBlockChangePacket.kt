package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Multi Block Change | 0x10 | play | clientbound
 *
 * @property chunkX chunkX
 * @property chunkZ chunkZ
 * @see <a href="https://wiki.vg/Protocol#Multi_Block_Change">https://wiki.vg/Protocol#Multi_Block_Change</a>
 */

@MinecraftPacket(id = 0x10, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class MultiBlockChangePacket(
    val chunkX: Int,
    val chunkZ: Int,
) : ClientBoundPacket {

    companion object : PacketSerializer<MultiBlockChangePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): MultiBlockChangePacket {
            val chunkX = input.readInt()
            val chunkZ = input.readInt()

            return MultiBlockChangePacket(chunkX, chunkZ)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: MultiBlockChangePacket) {
            output.writeInt(value.chunkX)
            output.writeInt(value.chunkZ)
        }
    }
}
