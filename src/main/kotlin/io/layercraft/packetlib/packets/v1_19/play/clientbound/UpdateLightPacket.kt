package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Light | 0x22 | play | clientbound
 *
 * @property chunkX chunkX
 * @property chunkZ chunkZ
 * @property trustEdges trustEdges
 * @see <a href="https://wiki.vg/Protocol#Update_Light">https://wiki.vg/Protocol#Update_Light</a>
 */

@MinecraftPacket(id = 0x22, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class UpdateLightPacket(
    val chunkX: Int, // varint
    val chunkZ: Int, // varint
    val trustEdges: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<UpdateLightPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateLightPacket {
            val chunkX = input.readVarInt()
            val chunkZ = input.readVarInt()
            val trustEdges = input.readBoolean()

            return UpdateLightPacket(chunkX, chunkZ, trustEdges)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateLightPacket) {
            output.writeVarInt(value.chunkX)
            output.writeVarInt(value.chunkZ)
            output.writeBoolean(value.trustEdges)
        }
    }
}