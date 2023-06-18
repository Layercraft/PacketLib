package io.layercraft.packetlib

import io.layercraft.packetlib.packets.MAX_PACKET_SIZE
import io.layercraft.packetlib.packets.Packet
import io.layercraft.packetlib.packets.PacketSerializer
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import java.nio.ByteBuffer

object TranslatorAPI {

    fun <T : Packet> decodeFromByteArray(bytes: ByteArray, serializer: PacketSerializer<T>): T {
        val byteBuffer = ByteBuffer.wrap(bytes)
        val deserialize = MinecraftByteBufferDeserialize(byteBuffer)

        return serializer.deserialize(deserialize)
    }

    fun <T : Packet> encodeToByteArray(value: T, serializer: PacketSerializer<T>): ByteArray {
        val byteBuffer = ByteBuffer.allocate(MAX_PACKET_SIZE)
        val serialize = MinecraftByteBufferSerialize(byteBuffer)

        serializer.serialize(serialize, value)

        val size = byteBuffer.position()
        val byteArray = ByteArray(size)
        byteBuffer.get(0, byteArray, 0, size)

        return byteArray
    }
}

class MinecraftByteBufferDeserialize(override val input: ByteBuffer) : MCProtocolDeserializer<ByteBuffer> {
    override val remaining: Int
        get() = input.remaining()

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

class MinecraftByteBufferSerialize(override val output: ByteBuffer) : MCProtocolSerializer<ByteBuffer> {
    override fun writeByte(input: Byte) {
        output.put(input)
    }

    override fun writeBytes(input: ByteArray) {
        output.put(input)
    }

    override fun writeBoolean(input: Boolean) {
        output.put(if (input) 1.toByte() else 0.toByte())
    }

    override fun writeInt(input: Int) {
        output.putInt(input)
    }

    override fun writeLong(input: Long) {
        output.putLong(input)
    }

    override fun writeShort(input: Short) {
        output.putShort(input)
    }

    override fun writeFloat(input: Float) {
        output.putFloat(input)
    }

    override fun writeDouble(input: Double) {
        output.putDouble(input)
    }

    override fun writeUByte(input: UByte) {
        output.put(input.toByte())
    }

    override fun writeUShort(input: UShort) {
        output.putShort(input.toShort())
    }
}