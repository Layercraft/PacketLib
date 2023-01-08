package io.layercraft.packetlib.utils.stream

import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import java.io.ByteArrayInputStream
import java.io.DataInputStream

class MinecraftInputStreamDeserialize(override val input: ByteArrayInputStream) : MinecraftProtocolDeserializeInterface<ByteArrayInputStream> {

    private val dataInputStream = DataInputStream(input)
    override val remaining: Int
        get() = dataInputStream.available()

    override fun readByte(): Byte = input.read().toByte()

    override fun readBytes(): ByteArray = dataInputStream.readBytes()

    override fun readBytes(n: Int): ByteArray = dataInputStream.readNBytes(n)

    override fun readBoolean(): Boolean = dataInputStream.readBoolean()

    override fun readInt(): Int = dataInputStream.readInt()

    override fun readLong(): Long = dataInputStream.readLong()

    override fun readShort(): Short = dataInputStream.readShort()

    override fun readFloat(): Float = dataInputStream.readFloat()

    override fun readDouble(): Double = dataInputStream.readDouble()

    override fun readUByte(): UByte = dataInputStream.read().toUByte()

    override fun readUShort(): UShort = dataInputStream.readShort().toUShort()
}