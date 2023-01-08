package io.layercraft.packetlib.utils.stream

import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import java.io.DataInputStream

class MinecraftInputStreamDeserialize(override val input: DataInputStream) : MinecraftProtocolDeserializeInterface<DataInputStream> {

    override val remaining: Int
        get() = input.available()

    override fun readByte(): Byte = input.read().toByte()

    override fun readBytes(): ByteArray = input.readBytes()

    override fun readBytes(n: Int): ByteArray = input.readNBytes(n)

    override fun readBoolean(): Boolean = input.readBoolean()

    override fun readInt(): Int = input.readInt()

    override fun readLong(): Long = input.readLong()

    override fun readShort(): Short = input.readShort()

    override fun readFloat(): Float = input.readFloat()

    override fun readDouble(): Double = input.readDouble()

    override fun readUByte(): UByte = input.read().toUByte()

    override fun readUShort(): UShort = input.readShort().toUShort()
}