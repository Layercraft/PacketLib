package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Section Blocks | 0x40 | play | clientbound
 *
 * @property suppressLightUpdates suppressLightUpdates
 * @see <a href="https://wiki.vg/Protocol#Update_Section_Blocks">https://wiki.vg/Protocol#Update_Section_Blocks</a>
 */

@MinecraftPacket(id = 0x40, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class MultiBlockChangePacket(
    val suppressLightUpdates: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<MultiBlockChangePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): MultiBlockChangePacket {
            val suppressLightUpdates = input.readBoolean()

            return MultiBlockChangePacket(suppressLightUpdates)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: MultiBlockChangePacket) {
            output.writeBoolean(value.suppressLightUpdates)
        }
    }
}