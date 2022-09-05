package io.layercraft.translator.serialization

import io.ktor.utils.io.core.*
import io.layercraft.translator.types.Position
import io.layercraft.translator.utils.MINECRAFT_MAX_CHAT_LENGTH
import io.layercraft.translator.utils.MINECRAFT_MAX_IDENTIFIER_LENGTH
import io.layercraft.translator.utils.minecraft
import java.util.*

@OptIn(ExperimentalUnsignedTypes::class)
object MinecraftProtocolDeserializeUtils: MinecraftProtocolDeserializeInterface {

    override fun readBoolean(input: Input): Boolean = input.readByte() != 0.toByte()

    override fun readByte(input: Input): Byte = input.readByte()

    override fun readUByte(input: Input): UByte = input.readUByte()

    override fun readShort(input: Input): Short = input.readShort()

    override fun readUShort(input: Input): UShort = input.readUShort()

    override fun readInt(input: Input): Int = input.readInt()

    override fun readVarInt(input: Input): Int = input.minecraft.readVarInt()

    override fun readLong(input: Input): Long = input.readLong()

    override fun readVarLong(input: Input): Long = input.minecraft.readVarLong()

    override fun readFloat(input: Input): Float = input.readFloat()

    override fun readDouble(input: Input): Double = input.readDouble()

    override fun readString(input: Input, n: Int): String = input.minecraft.readString(n)

    override fun readChat(input: Input): String = input.minecraft.readString(MINECRAFT_MAX_CHAT_LENGTH)

    override fun readIdentifier(input: Input): String = input.minecraft.readString(MINECRAFT_MAX_IDENTIFIER_LENGTH)

    override fun readVarIntByteArray(input: Input): ByteArray = input.readBytes(input.minecraft.readVarInt())

    override fun <T> readVarIntArray(input: Input, decoder: (input: Input) -> Unit): Array<T> {
        val size = input.minecraft.readVarInt()
        val array = arrayOfNulls<Any>(size)

        for (i in 0 until size) {
            array[i] = decoder(input)
        }

        @Suppress("UNCHECKED_CAST")
        return array as Array<T>
    }

    override fun readPosition(input: Input): Position = Position.longToPosition(input.readLong())

    override fun readUUID(input: Input): UUID {
        val most = input.readLong()
        val least = input.readLong()

        return UUID(most, least)
    }


}
