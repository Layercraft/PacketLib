package io.layercraft.packetlib.utils

import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import java.nio.ByteBuffer

class MinecraftByteBufferDeserialize(override val input: ByteBuffer) : MinecraftProtocolDeserializeInterface<ByteBuffer> {
    override val remaining: Long
        get() = input.remaining().toLong()

    override fun readByte(): Byte = input.get()

    override fun readBytes(): ByteArray {
        val bytes = ByteArray(input.remaining())
        input.get(bytes)
        return bytes
    }

    override fun readBytes(n: Int): ByteArray {
        val bytes = ByteArray(n)
        input.get(bytes)
        return bytes
    }

    override fun readBoolean(): Boolean = input.get() == 1.toByte()

    override fun readInt(): Int = input.int

    override fun readLong(): Long = input.long

    override fun readShort(): Short = input.short

    override fun readFloat(): Float = input.float

    override fun readDouble(): Double = input.double

    override fun readUByte(): UByte = input.get().toUByte()

    override fun readUShort(): UShort = input.short.toUShort()
}