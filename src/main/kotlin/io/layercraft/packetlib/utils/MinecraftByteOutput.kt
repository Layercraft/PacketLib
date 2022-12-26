package io.layercraft.packetlib.utils

import io.ktor.utils.io.core.*
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

@JvmInline
value class MinecraftByteOutput(override val output: Output) : MinecraftProtocolSerializeInterface<Output> {
    override fun writeByte(input: Byte) = output.writeByte(input)
    override fun writeBytes(input: ByteArray) = output.writeFully(input)

    override fun writeBoolean(input: Boolean) = output.writeByte(if (input) 1 else 0)
    override fun writeInt(input: Int) = output.writeInt(input)
    override fun writeLong(input: Long) = output.writeLong(input)
    override fun writeShort(input: Short) = output.writeShort(input)
    override fun writeFloat(input: Float) = output.writeFloat(input)
    override fun writeDouble(input: Double) = output.writeDouble(input)

    override fun writeUByte(input: UByte) = output.writeByte(input.toByte())
    override fun writeUShort(input: UShort) = output.writeShort(input.toShort())
}