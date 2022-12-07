package io.layercraft.translator.packets.play.data.statistic

enum class StatisticCategories(
    val id: Int,
    val identifier: String,
    val registry: StatisticCategoriesRegistry
) {
    MINED(0, "minecraft.mined", StatisticCategoriesRegistry.BLOCKS),
    CRAFTED(1, "minecraft.crafted", StatisticCategoriesRegistry.ITEMS),
    USED(2, "minecraft.used", StatisticCategoriesRegistry.ITEMS),
    BROKEN(3, "minecraft.broken", StatisticCategoriesRegistry.ITEMS),
    PICKED_UP(4, "minecraft.picked_up", StatisticCategoriesRegistry.ITEMS),
    DROPPED(5, "minecraft.dropped", StatisticCategoriesRegistry.ITEMS),
    KILLED(6, "minecraft.killed", StatisticCategoriesRegistry.ENTITIES),
    KILLED_BY(7, "minecraft.killed_by", StatisticCategoriesRegistry.ENTITIES),
    CUSTOM(8, "minecraft.custom", StatisticCategoriesRegistry.CUSTOM);

    companion object {
        fun fromId(id: Int): StatisticCategories {
            return values().first { it.id == id }
        }
    }
}