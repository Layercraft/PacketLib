package io.layercraft.packetlib.utils.bytebuffer

import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.nio.ByteBuffer

class MinecraftByteBufferSerialize(override val output: ByteBuffer) : MinecraftProtocolSerializeInterface<ByteBuffer> {
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