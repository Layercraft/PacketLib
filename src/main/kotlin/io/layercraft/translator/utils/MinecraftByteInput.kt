package io.layercraft.translator.utils

import io.ktor.utils.io.core.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.types.Position
import java.util.*
import kotlin.math.roundToInt

@JvmInline
value class MinecraftByteInput(override val input: Input): MinecraftProtocolDeserializeInterface<Input> {

    override fun readBoolean(): Boolean = input.readByte() != 0.toByte()

    override fun readByte(): Byte = input.readByte()

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun readUByte(): UByte = input.readUByte()

    override fun readShort(): Short = input.readShort()

    @OptIn(ExperimentalUnsignedTypes::class)
    override fun readUShort(): UShort = input.readUShort()

    override fun readInt(): Int = input.readInt()

    override fun readVarInt(): Int = MinecraftVarIntUtils.readVarInt(input)

    override fun readLong(): Long = input.readLong()

    override fun readVarLong(): Long = MinecraftVarLongUtils.readVarLong(input)

    override fun readFloat(): Float = input.readFloat()

    override fun readDouble(): Double = input.readDouble()

    override fun readString(n: Int): String = MinecraftStringUtils.readString(n, input)

    override fun readChat(): String = readString(MINECRAFT_MAX_CHAT_LENGTH)

    override fun readIdentifier(): String = readString(MINECRAFT_MAX_IDENTIFIER_LENGTH)

    override fun readVarIntByteArray(): ByteArray = input.readBytes(readVarInt())

    override fun <T> readVarIntArray(decoder: (input: MinecraftProtocolDeserializeInterface<Input>) -> T): List<T> {
        val size = readVarInt()

        return (1..size).map { decoder(this) }.toList()
    }

    override fun readRemainingByteArray(): ByteArray = input.readBytes()

    override fun <T> readRemainingArray(decoder: (input: MinecraftProtocolDeserializeInterface<Input>) -> T): List<T> {
        val list: MutableList<T> = mutableListOf()

        while (input.remaining > 0) {
            list.add(decoder(this))
        }

        return list.toList()
    }

    override fun readPosition(): Position = Position.longToPosition(input.readLong())

    override fun readUUID(): UUID {
        val most = input.readLong()
        val least = input.readLong()

        return UUID(most, least)
    }

    override fun readAngle(): Float {
        return (input.readByte() * 360.0f / 256f)
    }
}
