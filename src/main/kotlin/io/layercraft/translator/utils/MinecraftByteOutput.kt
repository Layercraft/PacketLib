package io.layercraft.translator.utils

import io.ktor.utils.io.core.*
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.translator.types.Position
import java.util.*

@JvmInline
value class MinecraftByteOutput(override val output: Output): MinecraftProtocolSerializeInterface<Output> {

    override fun writeBoolean(input: Boolean) = output.writeByte(if (input) 1 else 0)

    override fun writeByte(input: Byte) = output.writeByte(input)

    override fun writeUByte(input: UByte) = output.writeByte(input.toByte())

    override fun writeShort(input: Short) = output.writeShort(input)

    override fun writeUShort(input: UShort) = output.writeShort(input.toShort())

    override fun writeInt(input: Int) = output.writeInt(input)

    override fun writeVarInt(input: Int) = MinecraftVarIntUtils.writeVarInt(input, output)

    override fun writeLong(input: Long) = output.writeLong(input)

    override fun writeVarLong(input: Long) = MinecraftVarLongUtils.writeVarLong(input, output)

    override fun writeFloat(input: Float) = output.writeFloat(input)

    override fun writeDouble(input: Double) = output.writeDouble(input)

    override fun writeString(input: String, n: Int) = MinecraftStringUtils.writeString(n, input, output)

    override fun writeChat(input: String) = writeString(input, MINECRAFT_MAX_CHAT_LENGTH)

    override fun writeIdentifier(input: String) = writeString(input, MINECRAFT_MAX_IDENTIFIER_LENGTH)

    override fun writeVarIntByteArray(input: ByteArray) {
        writeVarInt(input.size)
        output.writeFully(input)
    }

    override fun <T> writeVarIntArray(input: List<T>, encoder: (value: T, output: MinecraftProtocolSerializeInterface<Output>) -> Unit) {
        writeVarInt(input.size)
        input.forEach { encoder(it, this) }
    }

    override fun writeRemainingByteArray(input: ByteArray) = output.writeFully(input)

    override fun <T> writeRemainingArray(input: List<T>, encoder: (value: T, output: MinecraftProtocolSerializeInterface<Output>) -> Unit) = input.forEach { encoder(it, this) }

    override fun writePosition(input: Position) = output.writeLong(Position.positionToLong(input))

    override fun writeUUID(input: UUID) {
        output.writeLong(input.mostSignificantBits)
        output.writeLong(input.leastSignificantBits)
    }

    override fun writeAngle(input: Float) {
        output.writeByte((input * 256f / 360f).toInt().toByte())
    }
}
