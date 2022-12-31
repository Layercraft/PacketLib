package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Award Statistics | 0x04 | play | clientbound
 *
 * @property entries entries
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Award_Statistics">https://wiki.vg/Protocol#Award_Statistics</a>
 */

@MinecraftPacket(id = 0x04, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class StatisticsPacket(
    val entries: List<StatisticsPacketEntries>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<StatisticsPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): StatisticsPacket {
            val entries = input.readVarIntArray { arrayInput ->
                val categoryId = arrayInput.readVarInt()
                val statisticId = arrayInput.readVarInt()
                val value = arrayInput.readVarInt()

                return@readVarIntArray StatisticsPacketEntries(categoryId, statisticId, value)
            }

            return StatisticsPacket(entries)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: StatisticsPacket) {
            output.writeVarIntArray(value.entries) { arrayValue, arrayOutput ->
                arrayOutput.writeVarInt(arrayValue.categoryId)
                arrayOutput.writeVarInt(arrayValue.statisticId)
                arrayOutput.writeVarInt(arrayValue.value)
            }
        }
    }
}

/**
 * StatisticsPacketEntries | entries
 *
 * @property categoryId categoryId
 * @property statisticId statisticId
 * @property value value
*/
data class StatisticsPacketEntries(
    val categoryId: Int, // varint
    val statisticId: Int, // varint
    val value: Int, // varint
)
