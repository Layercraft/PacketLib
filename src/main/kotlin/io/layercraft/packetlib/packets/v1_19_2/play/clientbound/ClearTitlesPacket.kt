package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x0d | play | clientbound
 *
 * @property reset reset
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x0d, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ClearTitlesPacket(
    val reset: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<ClearTitlesPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ClearTitlesPacket {
            val reset = input.readBoolean()

            return ClearTitlesPacket(reset)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ClearTitlesPacket) {
            output.writeBoolean(value.reset)
        }
    }
}