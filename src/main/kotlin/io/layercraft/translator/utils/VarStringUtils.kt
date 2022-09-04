package io.layercraft.translator.utils

import io.layercraft.translator.exceptions.MinecraftProtocolDecodingException
import io.layercraft.translator.exceptions.MinecraftProtocolEncodingException
import io.layercraft.translator.utils.MinecraftVarIntUtils.readVarInt
import io.layercraft.translator.utils.MinecraftVarIntUtils.writeVarInt

const val MINECRAFT_MAX_STRING_LENGTH = 32767
const val MINECRAFT_MAX_CHAT_LENGTH = 262144
const val MINECRAFT_MAX_IDENTIFIER_LENGTH = 32767


object MinecraftStringUtils {

    inline fun readString(maxLength: Int, readByte: () -> Byte, readBytes: (length: Int) -> ByteArray): String {
        val length: Int = readVarInt { readByte() }
        val string: String = readBytes(length).toString(Charsets.UTF_8)

        if (length > maxLength * 4) throw MinecraftProtocolDecodingException("The received encoded string buffer length is longer than maximum allowed ($length > ${maxLength * 4})")
        if (length < 0) throw MinecraftProtocolDecodingException("The received encoded string buffer length is less than zero! Weird string!")
        if (string.length > maxLength) throw MinecraftProtocolDecodingException("The received string length is longer than maximum allowed (${string.length} > $maxLength)")

        return string
    }

    inline fun writeString(maxLength: Int, string: String, writeByte: (Byte) -> Unit, writeBytes: (ByteArray) -> Unit) {
        val bytes = string.toByteArray(Charsets.UTF_8)
        if (bytes.size > MINECRAFT_MAX_STRING_LENGTH) throw MinecraftProtocolEncodingException("String too big (was ${bytes.size} bytes encoded, max $MINECRAFT_MAX_STRING_LENGTH)")
        if (string.length > maxLength) throw MinecraftProtocolEncodingException("String too big (was ${string.length} characters, max $maxLength)")
        writeVarInt(bytes.size) { writeByte(it) }
        writeBytes(bytes)
    }
}
