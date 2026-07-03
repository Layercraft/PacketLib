package io.layercraft.packetlib.types

/**
 * Position
 *
 * @property x
 * @property y
 * @property z
 * @see <a href="https://minecraft.wiki/w/Java_Edition_protocol/Data_types#Position">Position data type</a>
 */
data class Position(
    val x: Int,
    val y: Int,
    val z: Int,
) {

    companion object {
        /** Encoding used since 1.14: x (26 bits) | z (26 bits) | y (12 bits). */
        fun positionToLong(position: Position): Long = with(position) {
            x.toLong() and 0x3FFFFFF shl 38 or (z.toLong() and 0x3FFFFFF shl 12) or (y.toLong() and 0xFFF)
        }

        fun longToPosition(long: Long): Position = Position(
            (long shr 38).toInt(),
            (long shl 52 shr 52).toInt(),
            (long shl 26 shr 38).toInt(),
        )

        /** Encoding used before 1.14: x (26 bits) | y (12 bits) | z (26 bits). */
        fun legacyPositionToLong(position: Position): Long = with(position) {
            x.toLong() and 0x3FFFFFF shl 38 or (y.toLong() and 0xFFF shl 26) or (z.toLong() and 0x3FFFFFF)
        }

        fun longToLegacyPosition(long: Long): Position = Position(
            (long shr 38).toInt(),
            (long shl 26 shr 52).toInt(),
            (long shl 38 shr 38).toInt(),
        )
    }
}
