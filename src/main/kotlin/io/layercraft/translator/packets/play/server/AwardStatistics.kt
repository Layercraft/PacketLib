package io.layercraft.translator.packets.play.server

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.packets.play.data.statistic.CustomStatisticType
import io.layercraft.translator.packets.play.data.statistic.StatisticCategories
import io.layercraft.translator.utils.mc

/**
 * Award statistics | 0x04 | play | client-bound
 * Sent as a response to Client Command (id 1). Will only send the changed values if previously requested.
 *
 * @property statistics Array of [Statistic]
 * @see <a href="https://wiki.vg/Protocol#Award_Statistics">https://wiki.vg/Protocol#Award_Statistics</a>
 */
@MinecraftPacket(0x04, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class AwardStatistics(
    val statistics: ArrayList<Statistic>
) : ClientBoundPacket {
    companion object : PacketSerializer<AwardStatistics> {
        override fun serialize(input: Input): AwardStatistics {
            val statistics = input.mc.readVarIntArray { Statistic(StatisticCategories.fromId(it.mc.readVarInt()), it.mc.readVarInt(), it.mc.readVarInt()) }

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

/**
 * Statistic
 *
 * @property categoryId Categories [StatisticCategories]
 * @property statisticId Blocks, Items, and Entities use block (not block state), item, and entity ids. Custom has this [CustomStatisticType] (unit only matters for clients).
 * @property value The amount to set it to.
 * @see <a href="https://wiki.vg/Protocol#Award_Statistics">https://wiki.vg/Protocol#Award_Statistics</a>
 */
data class Statistic(
    val categoryId: StatisticCategories,
    val statisticId: Int,
    val value: Int
) {
    constructor(customStatisticType: CustomStatisticType, value: Int) : this(StatisticCategories.CUSTOM, customStatisticType.id, value)
}
