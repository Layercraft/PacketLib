package io.layercraft.packetlib.serialization.types

import io.layercraft.packetlib.types.Position

object PositionSerializer {
    fun positionToLong(position: Position): Long = with(position) {
        x.toLong() and 0x3FFFFFF shl 38 or (z.toLong() and 0x3FFFFFF shl 12) or (y.toLong() and 0xFFF)
    }

    fun longToPosition(long: Long): Position = Position(
        (long shr 38).toInt(),
        (long and 0xFFF).toInt(),
        (long shl 26 shr 38).toInt(),
    )
}