package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Section Blocks | 0x40 | play | clientbound
 *
 * @param suppressLightUpdates suppressLightUpdates
 * @param records records
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Update_Section_Blocks">https://wiki.vg/Protocol#Update_Section_Blocks</a>
 */

@MinecraftPacket(id = 0x40, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class MultiBlockChangePacket(
    val suppressLightUpdates: Boolean,
    val records: List<Int>, // varint array of varint
) : ClientBoundPacket {
    companion object : PacketSerializer<MultiBlockChangePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): MultiBlockChangePacket {
            val suppressLightUpdates = input.readBoolean()
            val records = input.readVarIntArray { arrayInput -> arrayInput.readVarInt() }

            return MultiBlockChangePacket(suppressLightUpdates, records)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: MultiBlockChangePacket) {
            output.writeBoolean(value.suppressLightUpdates)
            output.writeVarIntArray(value.records) { arrayValue, arrayOutput -> arrayOutput.writeVarInt(arrayValue) }
        }
    }
}