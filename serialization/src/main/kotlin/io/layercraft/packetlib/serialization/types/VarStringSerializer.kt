package io.layercraft.packetlib.serialization.types

import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import java.io.EOFException

object VarStringSerializer {

    fun readString(maxLength: Int = MINECRAFT_MAX_STRING_LENGTH, input: MCProtocolDeserializer<*>): String {
        if (input.remaining <= 0) throw EOFException("Premature end of stream")

        val length: Int = input.readVarInt()

        if (length > maxLength * 4) throw IllegalArgumentException("The received encoded string buffer length is longer than maximum allowed ($length > ${maxLength * 4})")
        if (length < 0) throw IllegalArgumentException("The received encoded string buffer length is less than zero! Weird string!")

        val string: String = input.readBytes(length).toString(Charsets.UTF_8)

        if (string.length > maxLength) throw IllegalArgumentException("The received string length is longer than maximum allowed (${string.length} > $maxLength)")

        return string
    }

    fun writeString(maxLength: Int = MINECRAFT_MAX_STRING_LENGTH, string: String, output: MCProtocolSerializer<*>) {
        val bytes = string.toByteArray(Charsets.UTF_8)
        if (bytes.size > MINECRAFT_MAX_STRING_LENGTH) throw IllegalArgumentException("String too big (was ${bytes.size} bytes encoded, max $MINECRAFT_MAX_STRING_LENGTH)")
        if (string.length > maxLength) throw IllegalArgumentException("String too big (was ${string.length} characters, max $maxLength)")
        output.writeVarInt(bytes.size)
        output.writeBytes(bytes)
    }
}