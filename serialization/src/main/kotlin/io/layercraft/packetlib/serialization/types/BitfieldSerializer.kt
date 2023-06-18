package io.layercraft.packetlib.serialization.types

import io.layercraft.packetlib.types.Bitfield

object BitfieldSerializer {

    fun valueOf(bytes: ByteArray): Bitfield {
        return Bitfield(bytes)
    }

    fun toByteArray(bitfield: Bitfield): ByteArray {
        return bitfield.bytes
    }
}