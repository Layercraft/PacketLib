package io.layercraft.translator.packets.play.data.statistic

/**
 * Statistic
 *
 * @property categoryId Categories [StatisticCategories]
 * @property statisticId Blocks, Items, and Entities use block (not block state), item, and entity ids. Custom has this [CustomStatisticType] (unit only matters for clients).
 * @property value The amount to set it to.
 * @see <a href="https://wiki.vg/Protocol#Award_Statistics">https://wiki.vg/Protocol#Award_Statistics</a>
 */
data class AwardStatistic(
    val categoryId: StatisticCategories,
    val statisticId: Int,
    val value: Int,
) {
    constructor(customStatisticType: CustomStatisticType, value: Int) : this(StatisticCategories.CUSTOM, customStatisticType.id, value)
}