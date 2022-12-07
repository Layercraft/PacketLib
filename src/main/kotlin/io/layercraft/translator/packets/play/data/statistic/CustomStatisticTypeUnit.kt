package io.layercraft.translator.packets.play.data.statistic

/**
 * Units:
 * - None: just a normal number (formatted with 0 decimal places)
 * - Damage: value is 10 times the normal amount
 * - Distance: a distance in centimeters (hundredths of blocks)
 * - Time: a time span in ticks
 */
enum class CustomStatisticTypeUnit(val value: String) {
    NONE("None"),
    TIME("Time"),
    DISTANCE("Distance"),
    DAMAGE("Damage")
}