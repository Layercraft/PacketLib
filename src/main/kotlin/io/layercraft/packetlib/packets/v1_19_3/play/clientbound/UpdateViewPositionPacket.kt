package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Center Chunk | 0x4a | play | clientbound
 *
 * @param chunkX chunkX
 * @param chunkZ chunkZ
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Center_Chunk">https://wiki.vg/Protocol#Set_Center_Chunk</a>
 */

data class UpdateViewPositionPacket(
    val chunkX: Int, // varint
    val chunkZ: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<UpdateViewPositionPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): UpdateViewPositionPacket {
            val chunkX = input.readVarInt()
            val chunkZ = input.readVarInt()

            return UpdateViewPositionPacket(chunkX, chunkZ)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: UpdateViewPositionPacket) {
            output.writeVarInt(value.chunkX)
            output.writeVarInt(value.chunkZ)
        }
    }
}