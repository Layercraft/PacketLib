package io.layercraft.translator.utils

import io.ktor.utils.io.core.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.types.Position
import java.util.*

@JvmInline
value class MinecraftByteInput(private val buffer: Input): MinecraftProtocolDeserializeInterface {
    override fun readBoolean(): Boolean = buffer.readByte() != 0.toByte()

    override fun readByte(): Byte = buffer.readByte()

    override fun readUByte(): UByte = buffer.readUByte()

    override fun readShort(): Short = buffer.readShort()

    override fun readUShort(): UShort = buffer.readUShort()

    override fun readInt(): Int = buffer.readInt()

    override fun readVarInt(): Int = MinecraftVarIntUtils.readVarInt(buffer::readByte)

    override fun readLong(): Long = buffer.readLong()

    override fun readVarLong(): Long = MinecraftVarLongUtils.readVarLong(buffer::readByte)

    override fun readFloat(): Float = buffer.readFloat()

    override fun readDouble(): Double = buffer.readDouble()

    override fun readString(n: Int): String = MinecraftStringUtils.readString(n, buffer::readByte, buffer::readBytes)

    override fun readChat(): String = readString(MINECRAFT_MAX_CHAT_LENGTH)

    override fun readIdentifier(): String = readString(MINECRAFT_MAX_IDENTIFIER_LENGTH)

    override fun readVarIntByteArray(): ByteArray = buffer.readBytes(readVarInt())

    override fun <T> readVarIntArray(decoder: (input: Input) -> T): ArrayList<T> {
        val size = readVarInt()
        val arrayList = ArrayList<T>(size)

        for (i in 0 until size) {
            arrayList.add(decoder(buffer))
        }

        return arrayList

    }

    override fun readRemainingByteArray(): ByteArray = buffer.readBytes()

    override fun <T> readRemainingArray(decoder: (input: Input) -> T): ArrayList<T> {
        val arrayList = ArrayList<T>()

        while (buffer.remaining > 0) {
            arrayList.add(decoder(buffer))
        }

        return arrayList
    }

    override fun readPosition(): Position = Position.longToPosition(buffer.readLong())

    override fun readUUID(): UUID {
        val most = buffer.readLong()
        val least = buffer.readLong()

        return UUID(most, least)
    }

    override fun readAngle(): Float {
        return buffer.readByte() * 360 / 256f
    }
}
