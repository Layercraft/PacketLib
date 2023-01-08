package io.layercraft.packetlib.utils.stream

import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

class MinecraftOutputStreamSerialize(override val output: ByteArrayOutputStream) : MinecraftProtocolSerializeInterface<ByteArrayOutputStream> {

    private val dataOutputStream = DataOutputStream(output)
    override fun writeByte(input: Byte) = dataOutputStream.writeByte(input.toInt())

    override fun writeBytes(input: ByteArray) = dataOutputStream.write(input)

    override fun writeBoolean(input: Boolean) = dataOutputStream.writeBoolean(input)

    override fun writeInt(input: Int) = dataOutputStream.writeInt(input)

    override fun writeLong(input: Long) = dataOutputStream.writeLong(input)

    override fun writeShort(input: Short) = dataOutputStream.writeShort(input.toInt())

    override fun writeFloat(input: Float) = dataOutputStream.writeFloat(input)

    override fun writeDouble(input: Double) = dataOutputStream.writeDouble(input)

    override fun writeUByte(input: UByte) = dataOutputStream.writeByte(input.toInt())

    override fun writeUShort(input: UShort) = dataOutputStream.writeShort(input.toInt())
}