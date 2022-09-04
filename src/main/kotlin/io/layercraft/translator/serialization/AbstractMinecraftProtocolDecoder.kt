package io.layercraft.translator.serialization

import io.ktor.utils.io.core.*
import io.layercraft.translator.utils.minecraft
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.internal.TaggedDecoder

@OptIn(InternalSerializationApi::class, ExperimentalUnsignedTypes::class)
abstract class AbstractMinecraftProtocolDecoder(protected val input: Input) : TaggedDecoder<ProtocolDesc>() {
    fun decodeUByte() = input.readUByte()
    fun decodeUShort() = input.readUShort()
    fun decodeUInt() = input.readUInt()
    fun decodeULong() = input.readULong()

    fun decodeVarInt() = input.minecraft.readVarInt()
    fun decodeVarLong() = input.minecraft.readVarLong()
}
