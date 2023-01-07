package io.layercraft.packetlib.types

class Bitfield(
    private val bytes: ByteArray,
) {
    fun toByteArray(): ByteArray {
        return bytes
    }
    companion object {
        fun valueOf(bytes: ByteArray): Bitfield {
            return Bitfield(bytes)
        }
    }
}