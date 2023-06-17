package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import io.layercraft.packetlib.types.Bitfield
/**
 * Update Section Blocks | 0x3f | play | clientbound
 *
 * @param chunkCoordinates chunkCoordinates (Name: x, Size: 22, Signed: True | Name: z, Size: 22, Signed: True | Name: y, Size: 20, Signed: True)
 * @param suppressLightUpdates suppressLightUpdates
 * @param records records
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Section_Blocks">https://wiki.vg/Protocol#Update_Section_Blocks</a>
 */

data class MultiBlockChangePacket(
    val chunkCoordinates: Bitfield,
    val suppressLightUpdates: Boolean,
    val records: List<Int>, // varint array of varint
) : ClientBoundPacket {
    companion object : PacketSerializer<MultiBlockChangePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): MultiBlockChangePacket {
            val chunkCoordinates = Bitfield.valueOf(input.readBytes(64))
            val suppressLightUpdates = input.readBoolean()
            val records = input.readVarIntArray { arrayInput -> arrayInput.readVarInt() }

            return MultiBlockChangePacket(chunkCoordinates, suppressLightUpdates, records)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: MultiBlockChangePacket) {
            output.writeBytes(value.chunkCoordinates.toByteArray())
            output.writeBoolean(value.suppressLightUpdates)
            output.writeVarIntArray(value.records) { arrayValue, arrayOutput -> arrayOutput.writeVarInt(arrayValue) }
        }
    }
}