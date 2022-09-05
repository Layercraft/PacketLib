package io.layercraft.translator.serialization

import io.ktor.utils.io.core.*
import io.layercraft.translator.types.Position
import java.util.*

interface MinecraftProtocolDeserializeInterface {

    //ALL Datatypes: https://wiki.vg/Protocol#Data_types

    fun readBoolean(input: Input): Boolean

    fun readByte(input: Input): Byte
    fun readUByte(input: Input): UByte

    fun readShort(input: Input): Short
    fun readUShort(input: Input): UShort

    fun readInt(input: Input): Int
    fun readVarInt(input: Input): Int

    fun readLong(input: Input): Long
    fun readVarLong(input: Input): Long

    fun readFloat(input: Input): Float
    fun readDouble(input: Input): Double

    fun readString(input: Input, n: Int): String
    fun readChat(input: Input): String
    fun readIdentifier(input: Input): String

    fun readVarIntByteArray(input: Input): ByteArray
    fun <T> readVarIntArray(input: Input, decoder: (input: Input) -> Unit): Array<T>

    fun readPosition(input: Input): Position
    fun readUUID(input: Input): UUID
    //fun readAngle(input: Input): Float

    //fun readEnum(input: Input, enum: Enum<*>, type: MinecraftEnumType): Enum<*>
}
