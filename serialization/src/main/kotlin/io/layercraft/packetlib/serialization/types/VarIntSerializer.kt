package io.layercraft.packetlib.serialization.types

import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import java.io.EOFException

object VarIntSerializer {
    fun readVarInt(input: MCProtocolDeserializer<*>): Int {
        if (input.remaining <= 0) throw EOFException("Premature end of stream")
        var numRead = 0
        var result = 0
        var read: Byte
        do {
            read = input.readByte()
            val value = (read.toInt() and MINECRAFT_VAR_NUMBER_SEGMENT_BITS)
            result = result or (value shl (7 * numRead))
            numRead++
            if (numRead > 5) {
                throw RuntimeException("VarInt is too big")
            }
        } while (read.toInt() and MINECRAFT_VAR_NUMBER_CONTINUE_BIT != 0)
        return result
    }

    fun writeVarInt(value: Int, output: MCProtocolSerializer<*>) {
        var write = value
        while (true) {
            if (write and -MINECRAFT_VAR_NUMBER_CONTINUE_BIT == 0) {
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