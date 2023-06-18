package io.layercraft.packetlib.types

/**
 * Position
 *
 * @property x
 * @property y
 * @property z
 * @see <a href="https://wiki.vg/Protocol#Position">https://wiki.vg/Protocol#Position</a>
 */
data class Position(
    val x: Int,
    val y: Int,
    val z: Int,
)