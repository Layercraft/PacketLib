package io.layercraft.translator.packets.play.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Clear Titles | 0x0D | play | client-bound
 *
 * Clear the client's current title information, with the option to also reset it.
 *
 * @property reset
 * @see <a href="https://wiki.vg/Protocol#Clear_Titles">https://wiki.vg/Protocol#Clear_Titles</a>
 */
@MinecraftPacket(0x0D, PacketState.PLAY, PacketDirection.CLIENTBOUND)
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