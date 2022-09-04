package io.layercraft.translator.serialization

import io.ktor.utils.io.core.*
import io.layercraft.translator.utils.minecraft
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.internal.TaggedEncoder

@OptIn(InternalSerializationApi::class, ExperimentalUnsignedTypes::class)
abstract class AbstractMinecraftProtocolEncoder(protected val output: Output) : TaggedEncoder<ProtocolDescriptor>() {
    fun encodeUByte(value: UByte) = output.writeUByte(value)
    fun encodeUShort(value: UShort) = output.writeUShort(value)
    fun encodeUInt(value: UInt) = output.writeUInt(value)
    fun encodeULong(value: ULong) = output.writeULong(value)

    fun encodeVarInt(value: Int) = output.minecraft.writeVarInt(value)
    fun encodeVarLong(value: Long) = output.minecraft.writeVarLong(value)
}
