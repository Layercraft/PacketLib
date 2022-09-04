package io.layercraft.translator.utils

import io.layercraft.translator.utils.MinecraftVarIntUtils.readVarInt
import io.layercraft.translator.utils.MinecraftVarIntUtils.writeVarInt

object ByteArrayUtils {

    inline fun readByteArray(readByte: () -> Byte, readBytes: (length: Int) -> ByteArray): ByteArray {
        val length = readVarInt(readByte)
        return readBytes(length)
    }

    inline fun writeByteArray(bytes: ByteArray, writeByte: (byte: Byte) -> Unit, writeBytes: (bytes: ByteArray) -> Unit) {
        writeVarInt(bytes.size, writeByte)
        writeBytes(bytes)
    }
}
