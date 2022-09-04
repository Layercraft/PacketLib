package io.layercraft.translator.utils

import io.ktor.utils.io.core.*

val Input.minecraft get() = MinecraftByteInput(this)
val Output.minecraft get() = MinecraftByteOutput(this)

@JvmInline
value class MinecraftByteInput(private val buffer: Input) {
    fun readString(maxLength: Int): String = MinecraftStringUtils.readString(maxLength, buffer::readByte, buffer::readBytes)

    fun readVarInt(): Int = MinecraftVarIntUtils.readVarInt(buffer::readByte)

    fun readVarLong(): Long = MinecraftVarLongUtils.readVarLong(buffer::readByte)
}

@JvmInline
value class MinecraftByteOutput(private val buffer: Output) {
    fun writeString(string: String, maxLength: Int) = MinecraftStringUtils.writeString(maxLength, string, buffer::writeByte, buffer::writeFully)

    fun writeVarInt(value: Int) = MinecraftVarIntUtils.writeVarInt(value, buffer::writeByte)

    fun writeVarLong(value: Long) = MinecraftVarLongUtils.writeVarLong(value, buffer::writeByte)
}
