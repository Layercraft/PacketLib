package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Award Statistics | 0x04 | play | clientbound
 *
 * @param entries list of StatisticsPacketEntries
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Award_Statistics">https://wiki.vg/Protocol#Award_Statistics</a>
 */

data class StatisticsPacket(
    val entries: List<StatisticsPacketEntries>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<StatisticsPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): StatisticsPacket {
            val entries = input.readVarIntArray { arrayInput ->
                val categoryId = arrayInput.readVarInt()
                val statisticId = arrayInput.readVarInt()
                val value = arrayInput.readVarInt()

                StatisticsPacketEntries(categoryId, statisticId, value)
            }

            return StatisticsPacket(entries)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: StatisticsPacket) {
            output.writeVarIntArray(value.entries) { arrayValue, arrayOutput ->
                arrayOutput.writeVarInt(arrayValue.categoryId)
                arrayOutput.writeVarInt(arrayValue.statisticId)
                arrayOutput.writeVarInt(arrayValue.value)
            }
        }
    }
}

/**
 * StatisticsPacketEntries
 *
 * @param categoryId categoryId
 * @param statisticId statisticId
 * @param value value
*/
data class StatisticsPacketEntries(
    val categoryId: Int, // varint
    val statisticId: Int, // varint
    val value: Int, // varint
)