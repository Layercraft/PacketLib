package io.layercraft.translator.utils

import kotlin.experimental.and
import kotlin.experimental.or

const val SEGMENT_BITS = 0x7F
const val CONTINUE_BIT = 0x80

object MinecraftVarInt {
    fun fromVarInt(bytes: ByteArray, offset: Int = 0): Int {
        var numRead = 0
        var result = 0
        var read: Byte
        do {
            read = bytes[offset + numRead]
            val value = (read.toInt() and SEGMENT_BITS)
            result = result or (value shl (7 * numRead))
            numRead++
            if (numRead > 5) {
                throw RuntimeException("VarInt is too big")
            }
        } while (read.toInt() and CONTINUE_BIT != 0)
        return result
    }

    fun toVarInt(value: Int): ByteArray {
        var value = value
        val buffer = ByteArray(5)
        var size = 0
        while (true) {
            if (value and -CONTINUE_BIT == 0) {
                buffer[size++] = value.toByte()
                return buffer.copyOf(size)
            }
            buffer[size++] = (value and SEGMENT_BITS or CONTINUE_BIT).toByte()
            value = value ushr 7
        }
    }

    inline fun readVarInt(
        readByte: () -> Byte,
    ): Int {
        var numRead = 0
        var result = 0
        var read: Byte
        do {
            read = readByte()
            val value = (read.toInt() and SEGMENT_BITS)
            result = result or (value shl 7 * numRead)
            numRead++
            if (numRead > 5) {
                throw RuntimeException("VarInt is too big")
            }
        } while (read and 128.toByte() != 0.toByte())
        return result
    }

    inline fun writeVarInt(
        value: Int,
        writeByte: (Byte) -> Unit,
    ) {
        var value = value
        do {
            var temp = (value and SEGMENT_BITS).toByte()
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value = value ushr 7
            if (value != 0) {
                temp = temp or 128.toByte()
            }
            writeByte(temp)
        } while (value != 0)
    }

    fun varIntBytesCount(
        value: Int,
    ): Int {
        return when {
            value and -0x80 == 0 -> 1
            value and -0x4000 == 0 -> 2
            value and -0x200000 == 0 -> 3
            value and -0x10000000 == 0 -> 4
            else -> 5
        }
    }
}

object MinecraftVarLong {
    fun fromVarLong(bytes: ByteArray, offset: Int = 0): Long {
        var numRead = 0
        var result: Long = 0
        var read: Byte
        do {
            read = bytes[offset + numRead]
            val value = (read.toLong() and SEGMENT_BITS.toLong())
            result = result or (value shl (7 * numRead))
            numRead++
            if (numRead > 10) {
                throw RuntimeException("VarLong is too big")
            }
        } while (read.toInt() and CONTINUE_BIT != 0)
        return result
    }

    fun toVarLong(value: Long): ByteArray {
        var value = value
        val buffer = ByteArray(10)
        var size = 0
        while (true) {
            if (value and -CONTINUE_BIT.toLong() == 0L) {
                buffer[size++] = value.toByte()
                return buffer.copyOf(size)
            }
            buffer[size++] = (value and SEGMENT_BITS.toLong() or CONTINUE_BIT.toLong()).toByte()
            value = value ushr 7
        }
    }

    inline fun readVarLong(
        readByte: () -> Byte,
    ): Long {
        var numRead = 0
        var result: Long = 0
        var read: Byte
        do {
            read = readByte()
            val value = (read and 127).toLong()
            result = result or (value shl 7 * numRead)
            numRead++
            if (numRead > 10) {
                throw RuntimeException("VarLong is too big")
            }
        } while (read and 128.toByte() != 0.toByte())
        return result
    }

    inline fun writeVarLong(
        value: Long,
        writeByte: (Byte) -> Unit,
    ) {
        var value = value
        do {
            var temp = (value and 127).toByte()
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value = value ushr 7
            if (value != 0L) {
                temp = temp or 128.toByte()
            }
            writeByte(temp)
        } while (value != 0L)
    }

    fun varLongBytesCount(
        value: Long,
    ): Int {
        return when(value){
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
