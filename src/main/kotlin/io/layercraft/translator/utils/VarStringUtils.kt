package io.layercraft.translator.utils

import io.ktor.utils.io.core.*
import io.layercraft.translator.exceptions.MinecraftProtocolDecodingException
import io.layercraft.translator.exceptions.MinecraftProtocolEncodingException
import java.io.EOFException
import kotlin.text.toByteArray

const val MINECRAFT_MAX_STRING_LENGTH = 32767
const val MINECRAFT_MAX_CHAT_LENGTH = 262144
const val MINECRAFT_MAX_IDENTIFIER_LENGTH = 32767


object MinecraftStringUtils {

    fun readString(maxLength: Int = MINECRAFT_MAX_STRING_LENGTH, input: Input): String {
        if (input.endOfInput) throw EOFException("Premature end of stream")

        val length: Int = input.mc.readVarInt()

        if (length > maxLength * 4) throw MinecraftProtocolDecodingException("The received encoded string buffer length is longer than maximum allowed ($length > ${maxLength * 4})")
        if (length < 0) throw MinecraftProtocolDecodingException("The received encoded string buffer length is less than zero! Weird string!")

        val string: String = input.readBytes(length).toString(Charsets.UTF_8)

        if (string.length > maxLength) throw MinecraftProtocolDecodingException("The received string length is longer than maximum allowed (${string.length} > $maxLength)")

        return string
    }

    fun writeString(maxLength: Int = MINECRAFT_MAX_STRING_LENGTH, string: String, output: Output) {
        val bytes = string.toByteArray(Charsets.UTF_8)
        if (bytes.size > MINECRAFT_MAX_STRING_LENGTH) throw MinecraftProtocolEncodingException("String too big (was ${bytes.size} bytes encoded, max $MINECRAFT_MAX_STRING_LENGTH)")
        if (string.length > maxLength) throw MinecraftProtocolEncodingException("String too big (was ${string.length} characters, max $maxLength)")
        output.mc.writeVarInt(bytes.size)
        output.writeFully(bytes)
    }
}
