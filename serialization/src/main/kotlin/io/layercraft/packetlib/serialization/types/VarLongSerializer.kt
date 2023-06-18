package io.layercraft.packetlib.serialization.types

import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import java.io.EOFException

object VarLongSerializer {
    fun readVarLong(input: MCProtocolDeserializer<*>): Long {
        if (input.remaining <= 0) throw EOFException("Premature end of stream")
        var numRead = 0
        var result: Long = 0
        var read: Byte
        do {
            read = input.readByte()
            val value = (read.toLong() and MINECRAFT_VAR_NUMBER_SEGMENT_BITS.toLong())
            result = result or (value shl (7 * numRead))
            numRead++
            if (numRead > 10) {
                throw RuntimeException("VarLong is too big")
            }
        } while (read.toInt() and MINECRAFT_VAR_NUMBER_CONTINUE_BIT != 0)
        return result
    }

    fun writeVarLong(value: Long, output: MCProtocolSerializer<*>) {
        var write = value
        while (true) {
            if (write and -MINECRAFT_VAR_NUMBER_CONTINUE_BIT.toLong() == 0L) {
                output.writeByte(write.toByte())
                return
            }
            output.writeByte((write and MINECRAFT_VAR_NUMBER_SEGMENT_BITS.toLong() or MINECRAFT_VAR_NUMBER_CONTINUE_BIT.toLong()).toByte())
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