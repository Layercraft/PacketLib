package io.layercraft.translator.serialization

import io.ktor.utils.io.core.*
import io.layercraft.translator.types.Position
import io.layercraft.translator.utils.MINECRAFT_MAX_CHAT_LENGTH
import io.layercraft.translator.utils.MINECRAFT_MAX_IDENTIFIER_LENGTH
import io.layercraft.translator.utils.minecraft
import java.util.*

object MinecraftProtocolSerializeUtils : MinecraftProtocolSerializeInterface {
    override fun writeBoolean(input: Boolean, output: Output) = output.writeByte(if (input) 1 else 0)

    override fun writeByte(input: Byte, output: Output) = output.writeByte(input)

    override fun writeUByte(input: UByte, output: Output) = output.writeByte(input.toByte())

    override fun writeShort(input: Short, output: Output) = output.writeShort(input)

    override fun writeUShort(input: UShort, output: Output) = output.writeShort(input.toShort())

    override fun writeInt(input: Int, output: Output) = output.writeInt(input)

    override fun writeVarInt(input: Int, output: Output) = output.minecraft.writeVarInt(input)

    override fun writeLong(input: Long, output: Output) = output.writeLong(input)

    override fun writeVarLong(input: Long, output: Output) = output.minecraft.writeVarLong(input)

    override fun writeFloat(input: Float, output: Output) = output.writeFloat(input)

    override fun writeDouble(input: Double, output: Output) = output.writeDouble(input)

    override fun writeString(input: String, n: Int, output: Output) = output.minecraft.writeString(input, n)

    override fun writeChat(input: String, output: Output) = output.minecraft.writeString(input, MINECRAFT_MAX_CHAT_LENGTH)

    override fun writeIdentifier(input: String, output: Output) = output.minecraft.writeString(input, MINECRAFT_MAX_IDENTIFIER_LENGTH)

    override fun writeVarIntByteArray(input: ByteArray, output: Output) {
        output.minecraft.writeVarInt(input.size)
        output.writeFully(input)
    }

    override fun <T> writeVarIntArray(input: Array<T>, output: Output, encoder: (value: T, output: Output) -> Unit) {
        output.minecraft.writeVarInt(input.size)
        input.forEach { encoder(it, output) }
    }

    override fun writePosition(input: Position, output: Output) = output.writeLong(Position.positionToLong(input))

    override fun writeUUID(input: UUID, output: Output) {
        output.writeLong(input.mostSignificantBits)
        output.writeLong(input.leastSignificantBits)
    }
}
