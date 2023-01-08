package io.layercraft.packetlib.utils.stream

import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.io.DataOutputStream

class MinecraftOutputStreamSerialize(override val output: DataOutputStream) : MinecraftProtocolSerializeInterface<DataOutputStream> {
    override fun writeByte(input: Byte) = output.writeByte(input.toInt())

    override fun writeBytes(input: ByteArray) = output.write(input)

    override fun writeBoolean(input: Boolean) = output.writeBoolean(input)

    override fun writeInt(input: Int) = output.writeInt(input)

    override fun writeLong(input: Long) = output.writeLong(input)

    override fun writeShort(input: Short) = output.writeShort(input.toInt())

    override fun writeFloat(input: Float) = output.writeFloat(input)

    override fun writeDouble(input: Double) = output.writeDouble(input)

    override fun writeUByte(input: UByte) = output.writeByte(input.toInt())

    override fun writeUShort(input: UShort) = output.writeShort(input.toInt())
}