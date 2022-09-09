package io.layercraft.translator.serialization

import io.ktor.utils.io.core.*
import io.layercraft.translator.types.Position
import java.util.*

interface MinecraftProtocolDeserializeInterface {

    //ALL Datatypes: https://wiki.vg/Protocol#Data_types

    fun readBoolean(): Boolean

    fun readByte(): Byte
    fun readUByte(): UByte

    fun readShort(): Short
    fun readUShort(): UShort

    fun readInt(): Int
    fun readVarInt(): Int

    fun readLong(): Long
    fun readVarLong(): Long

    fun readFloat(): Float
    fun readDouble(): Double

    fun readString(n: Int): String
    fun readChat(): String
    fun readIdentifier(): String

    fun readVarIntByteArray(): ByteArray
    fun <T> readVarIntArray(decoder: (input: Input) -> Unit): Array<T>

    fun readRemainingByteArray(): ByteArray
    fun <T> readRemainingArray(decoder: (input: Input) -> Unit): Array<T>

    fun readPosition(): Position
    fun readUUID(): UUID
    //fun readAngle(): Float

    //fun readEnum(, enum: Enum<*>, type: MinecraftEnumType): Enum<*>
}
