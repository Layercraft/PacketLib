package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Center Chunk | 0x4a | play | clientbound
 *
 * @param chunkX chunkX
 * @param chunkZ chunkZ
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17964#Set_Center_Chunk">https://wiki.vg/Protocol#Set_Center_Chunk</a>
 */

@MinecraftPacket(id = 0x4a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class UpdateViewPositionPacket(
    val chunkX: Int, // varint
    val chunkZ: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<UpdateViewPositionPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateViewPositionPacket {
            val chunkX = input.readVarInt()
            val chunkZ = input.readVarInt()

            return UpdateViewPositionPacket(chunkX, chunkZ)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateViewPositionPacket) {
            output.writeVarInt(value.chunkX)
            output.writeVarInt(value.chunkZ)
        }
    }
}