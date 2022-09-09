package io.layercraft.translator.utils

import io.ktor.utils.io.core.*
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.translator.types.Position
import java.util.*

@JvmInline
value class MinecraftByteOutput(private val buffer: Output): MinecraftProtocolSerializeInterface {
    override fun writeBoolean(input: Boolean) = buffer.writeByte(if (input) 1 else 0)

    override fun writeByte(input: Byte) = buffer.writeByte(input)

    override fun writeUByte(input: UByte) = buffer.writeByte(input.toByte())

    override fun writeShort(input: Short) = buffer.writeShort(input)

    override fun writeUShort(input: UShort) = buffer.writeShort(input.toShort())

    override fun writeInt(input: Int) = buffer.writeInt(input)

    override fun writeVarInt(input: Int) = MinecraftVarIntUtils.writeVarInt(input, buffer::writeByte)

    override fun writeLong(input: Long) = buffer.writeLong(input)

    override fun writeVarLong(input: Long) = MinecraftVarLongUtils.writeVarLong(input, buffer::writeByte)

    override fun writeFloat(input: Float) = buffer.writeFloat(input)

    override fun writeDouble(input: Double) = buffer.writeDouble(input)

    override fun writeString(input: String, n: Int) = MinecraftStringUtils.writeString(n, input, buffer::writeByte, buffer::writeFully)

    override fun writeChat(input: String) = writeString(input, MINECRAFT_MAX_CHAT_LENGTH)

    override fun writeIdentifier(input: String) = writeString(input, MINECRAFT_MAX_IDENTIFIER_LENGTH)

    override fun writeVarIntByteArray(input: ByteArray) {
        buffer.mc.writeVarInt(input.size)
        buffer.writeFully(input)
    }

    override fun <T> writeVarIntArray(input: List<T>, encoder: (value: T, output: Output) -> Unit) {
        buffer.mc.writeVarInt(input.size)
        input.forEach { encoder(it, buffer) }
    }

    override fun writeRemainingByteArray(input: ByteArray) = buffer.writeFully(input)

    override fun <T> writeRemainingArray(input: List<T>, encoder: (value: T, output: Output) -> Unit) = input.forEach { encoder(it, buffer) }

    override fun writePosition(input: Position) = buffer.writeLong(Position.positionToLong(input))

    override fun writeUUID(input: UUID) {
        buffer.writeLong(input.mostSignificantBits)
        buffer.writeLong(input.leastSignificantBits)
    }

    override fun writeAngle(input: Int) {
        if (input < 0 || input > 256) throw IllegalArgumentException("Angle must be between 0 and 256")
        buffer.writeByte(input.toByte())
    }
}
