package io.layercraft.translator.packets.play.clientbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.packets.play.data.statistic.AwardStatistic
import io.layercraft.translator.packets.play.data.statistic.StatisticCategories
import io.layercraft.translator.utils.mc

/**
 * Award statistics | 0x04 | play | client-bound
 * Sent as a response to Client Command (id 1). Will only send the changed values if previously requested.
 *
 * @property statistics Array of [AwardStatistic]
 * @see <a href="https://wiki.vg/Protocol#Award_Statistics">https://wiki.vg/Protocol#Award_Statistics</a>
 */
@MinecraftPacket(0x04, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class AwardStatistics(
    val statistics: List<AwardStatistic>
) : ClientBoundPacket {
    companion object : PacketSerializer<AwardStatistics> {
        override fun serialize(input: Input): AwardStatistics {
            val statistics = input.mc.readVarIntArray { AwardStatistic(StatisticCategories.fromId(it.mc.readVarInt()), it.mc.readVarInt(), it.mc.readVarInt()) }

            return AwardStatistics(statistics)
        }

        override fun deserialize(output: Output, value: AwardStatistics) {
            output.mc.writeVarIntArray(value.statistics) { it, arrayOutput ->
                arrayOutput.mc.writeVarInt(it.categoryId.id)
                arrayOutput.mc.writeVarInt(it.statisticId)
                arrayOutput.mc.writeVarInt(it.value)
            }
        }
    }
}

