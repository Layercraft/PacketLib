package io.layercraft.translator.utils

import io.layercraft.translator.exceptions.MinecraftProtocolDecodingException
import io.layercraft.translator.exceptions.MinecraftProtocolEncodingException
import io.layercraft.translator.utils.MinecraftVarInt.fromVarInt
import io.layercraft.translator.utils.MinecraftVarInt.toVarInt
import java.lang.RuntimeException

const val MINECRAFT_MAX_STRING_LENGTH = 32767

object MinecraftString {

    fun toString(
        maxLength: Int,
        bytes: ByteArray
    ): String {
        val length: Int = fromVarInt(bytes)
        val string = bytes.sliceArray(1 until length + 1).toString(Charsets.UTF_8)

        if (length > maxLength * 4) throw MinecraftProtocolDecodingException("The received encoded string buffer length is longer than maximum allowed (" + length + " > " + maxLength * 4 + ")")
        if (length < 0) throw MinecraftProtocolDecodingException("The received encoded string buffer length is less than zero! Weird string!")
        if (string.length > maxLength) throw MinecraftProtocolDecodingException("The received string length is longer than maximum allowed (" + string.length + " > " + maxLength + ")")

        return string
    }

    fun fromString(
        string: String
    ): ByteArray {
        val bytes = string.toByteArray(Charsets.UTF_8)
        if (bytes.size > MINECRAFT_MAX_STRING_LENGTH) throw MinecraftProtocolEncodingException("String too big (was " + bytes.size + " bytes encoded, max " + MINECRAFT_MAX_STRING_LENGTH + ")")
        return toVarInt(bytes.size) + bytes
    }
}
