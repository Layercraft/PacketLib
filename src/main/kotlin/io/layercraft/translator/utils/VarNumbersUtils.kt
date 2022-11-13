package io.layercraft.translator.utils

import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface
import java.io.EOFException

const val SEGMENT_BITS = 0x7F
const val CONTINUE_BIT = 0x80

object MinecraftVarIntUtils {
    fun readVarInt(input: MinecraftProtocolDeserializeInterface<*>): Int {
        if (input.remaining <= 0) throw EOFException("Premature end of stream")
        var numRead = 0
        var result = 0
        var read: Byte
        do {
            read = input.readByte()
            val value = (read.toInt() and SEGMENT_BITS)
            result = result or (value shl (7 * numRead))
            numRead++
            if (numRead > 5) {
                throw RuntimeException("VarInt is too big")
            }
        } while (read.toInt() and CONTINUE_BIT != 0)
        return result
    }

    fun writeVarInt(value: Int, output: MinecraftProtocolSerializeInterface<*>) {
        var write = value
        while (true) {
            if (write and -CONTINUE_BIT == 0) {
                output.writeByte(write.toByte())
                return
            }
            output.writeByte((write and 0x7F or 0x80).toByte())
            write = write ushr 7
        }
    }

    fun varIntBytesCount(value: Int): Int {
        return when {
            value and -0x80 == 0 -> 1
            value and -0x4000 == 0 -> 2
            value and -0x200000 == 0 -> 3
            value and -0x10000000 == 0 -> 4
            else -> 5
        }
    }
}

object MinecraftVarLongUtils {
    fun readVarLong(input: MinecraftProtocolDeserializeInterface<*>): Long {
        if (input.remaining <= 0) throw EOFException("Premature end of stream")
        var numRead = 0
        var result: Long = 0
        var read: Byte
        do {
            read = input.readByte()
            val value = (read.toLong() and SEGMENT_BITS.toLong())
            result = result or (value shl (7 * numRead))
            numRead++
            if (numRead > 10) {
                throw RuntimeException("VarLong is too big")
            }
        } while (read.toInt() and CONTINUE_BIT != 0)
        return result
    }

    fun writeVarLong(value: Long, output: MinecraftProtocolSerializeInterface<*>) {
        var write = value
        while (true) {
            if (write and -CONTINUE_BIT.toLong() == 0L) {
                output.writeByte(write.toByte())
                return
            }
            output.writeByte((write and SEGMENT_BITS.toLong() or CONTINUE_BIT.toLong()).toByte())
            write = write ushr 7
        }
    }

    fun varLongBytesCount(value: Long): Int {
        return when (value) {
            in 0..127 -> 1
            in 128..16383 -> 2
            in 16384..2097151 -> 3
            in 2097152..268435455 -> 4
            in 268435456..34359738367 -> 5
            in 34359738368..4398046511103 -> 6
            in 4398046511104..562949953421311 -> 7
            in 562949953421312..72057594037927935 -> 8
            in 72057594037927936..9223372036854775807 -> 9
            else -> 10
        }
    }
}
