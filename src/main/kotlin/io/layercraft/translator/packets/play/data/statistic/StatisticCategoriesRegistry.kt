package io.layercraft.translator.packets.play.data.statistic

enum class StatisticCategoriesRegistry(
    val value: String
) {
    BLOCKS("Blocks"),
    ITEMS("Items"),
    ENTITIES("Entities"),
    CUSTOM("Custom")
}