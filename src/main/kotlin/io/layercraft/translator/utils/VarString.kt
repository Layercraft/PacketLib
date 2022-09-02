package io.layercraft.translator.utils

import io.layercraft.translator.utils.MinecraftVarInt.fromVarInt
import io.layercraft.translator.utils.MinecraftVarInt.readVarInt
import io.layercraft.translator.utils.MinecraftVarInt.toVarInt
import io.layercraft.translator.utils.MinecraftVarInt.writeVarInt
import java.lang.RuntimeException

const val MINECRAFT_MAX_STRING_LENGTH = 32767

object MinecraftString {

    fun toString(
        maxLength: Int,
        bytes: ByteArray
    ): String {
        val length: Int = fromVarInt(bytes)
        val string = bytes.sliceArray(1 until length).toString(Charsets.UTF_8)

        if (length > maxLength * 4) {
            //throw MinecraftProtocolDecodingException("The received encoded string buffer length is longer than maximum allowed (" + length + " > " + maxLength * 4 + ")")
            throw RuntimeException()
        }

        if (length < 0) {
            throw RuntimeException()
            //throw MinecraftProtocolDecodingException("The received encoded string buffer length is less than zero! Weird string!")
        }

        if (string.length > maxLength) {
            throw RuntimeException()
            //throw MinecraftProtocolDecodingException("The received string length is longer than maximum allowed (" + string.length + " > " + maxLength + ")")
        }

        return string
    }

    fun fromString(
        string: String
    ): ByteArray {
        val bytes = string.toByteArray(Charsets.UTF_8)
        if (bytes.size > MINECRAFT_MAX_STRING_LENGTH) {
            throw RuntimeException()
            //throw MinecraftProtocolDecodingException("String too big (was " + length + " bytes encoded, max " + MINECRAFT_MAX_STRING_LENGTH + ")")
        }
        return toVarInt(bytes.size) + bytes
    }

    inline fun readString(
        maxLength: Int,
        readByte: () -> Byte,
        readBytes: (length: Int) -> ByteArray,
    ): String {
        val length: Int = readVarInt(readByte)
        return if (length > maxLength * 4) {
            throw RuntimeException()
            //throw MinecraftProtocolDecodingException("The received encoded string buffer length is longer than maximum allowed (" + length + " > " + maxLength * 4 + ")")
        } else if (length < 0) {
            throw RuntimeException()
            //throw MinecraftProtocolDecodingException("The received encoded string buffer length is less than zero! Weird string!")
        } else {
            val stringBuffer = readBytes(length).decodeToString()
            if (stringBuffer.length > maxLength) {
                throw RuntimeException()
                //throw MinecraftProtocolDecodingException("The received string length is longer than maximum allowed ($length > $maxLength)")
            } else {
                stringBuffer
            }
        }
    }

    inline fun writeString(
        string: String,
        writeByte: (Byte) -> Unit,
        writeFully: (ByteArray) -> Unit
    ) {
        val bytes = string.toByteArray()

        if (bytes.size > MINECRAFT_MAX_STRING_LENGTH) {
            throw RuntimeException()
            //throw MinecraftProtocolEncodingException("String too big (was " + bytes.size + " bytes encoded, max " + 32767 + ")")
        } else {
            writeVarInt(bytes.size, writeByte)
            writeFully(bytes)
        }
    }
}
