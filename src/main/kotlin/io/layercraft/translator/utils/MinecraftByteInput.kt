package io.layercraft.translator.utils

import io.ktor.utils.io.core.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import java.util.*

@JvmInline
value class MinecraftByteInput(override val input: Input) : MinecraftProtocolDeserializeInterface<Input> {
    override val remaining: Long get() = input.remaining

    override fun readByte(): Byte = input.readByte()
    override fun readBytes(): ByteArray = input.readBytes()
    override fun readBytes(n: Int): ByteArray = input.readBytes(n)

    override fun readBoolean(): Boolean = input.readByte() != 0.toByte()
    override fun readInt(): Int = input.readInt()
    override fun readLong(): Long = input.readLong()
    override fun readShort(): Short = input.readShort()
    override fun readFloat(): Float = input.readFloat()
    override fun readDouble(): Double = input.readDouble()

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun readUByte(): UByte = input.readUByte()

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun readUShort(): UShort = input.readUShort()
}