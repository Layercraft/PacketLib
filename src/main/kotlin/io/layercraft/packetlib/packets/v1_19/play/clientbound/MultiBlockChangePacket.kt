package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x3d | play | clientbound
 *
 * @property notTrustEdges notTrustEdges
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x3d, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class MultiBlockChangePacket(
    val notTrustEdges: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<MultiBlockChangePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): MultiBlockChangePacket {
            val notTrustEdges = input.readBoolean()

            return MultiBlockChangePacket(notTrustEdges)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: MultiBlockChangePacket) {
            output.writeBoolean(value.notTrustEdges)
        }
    }
}