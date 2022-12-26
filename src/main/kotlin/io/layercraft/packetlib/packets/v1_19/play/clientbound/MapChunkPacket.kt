package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x1f | play | clientbound
 *
 * @property x x
 * @property z z
 * @property heightmaps heightmaps
 * @property trustEdges trustEdges
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x1f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class MapChunkPacket(
    val x: Int,
    val z: Int,
    val heightmaps: ByteArray,
    val trustEdges: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<MapChunkPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): MapChunkPacket {
            val x = input.readInt()
            val z = input.readInt()
            val heightmaps = input.readNBT()
            val trustEdges = input.readBoolean()

            return MapChunkPacket(x, z, heightmaps, trustEdges)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: MapChunkPacket) {
            output.writeInt(value.x)
            output.writeInt(value.z)
            output.writeBytes(value.heightmaps)
            output.writeBoolean(value.trustEdges)
        }
    }
}