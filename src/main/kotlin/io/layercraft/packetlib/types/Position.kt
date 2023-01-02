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
) {

    companion object {
        fun positionToLong(position: Position): Long = with(position) {
            x.toLong() and 0x3FFFFFF shl 38 or (z.toLong() and 0x3FFFFFF shl 12) or (y.toLong() and 0xFFF)
        }

        fun longToPosition(long: Long): Position = Position(
            (long shr 38).toInt(),
            (long and 0xFFF).toInt(),
            (long shl 26 shr 38).toInt(),
        )
    }
}