package io.layercraft.translator.serialization

import io.ktor.utils.io.core.*
import io.layercraft.translator.MinecraftArraySizeType
import io.layercraft.translator.MinecraftEnumType
import io.layercraft.translator.MinecraftNumberType
import io.layercraft.translator.exceptions.MinecraftProtocolDecodingException
import io.layercraft.translator.utils.minecraft
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE

open class MinecraftProtocolDecoder(input: Input) : AbstractMinecraftProtocolDecoder(input) {
    private var currentIndex = 0
    private var elementsCount: Int = -100

    constructor(input: Input, elementCount: Int) : this(input) {
        this.elementsCount = elementCount
    }

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        if (elementsCount == -100) elementsCount = descriptor.elementsCount
        return if (elementsCount == currentIndex) DECODE_DONE else currentIndex++
    }

    override fun decodeTaggedBoolean(tag: ProtocolDescriptor): Boolean =
        when (val i = input.readByte()) {
            0x00.toByte() -> false
            0x01.toByte() -> true
            else -> throw MinecraftProtocolDecodingException("Expected boolean value (0 or 1), found $i")
        }

    override fun decodeTaggedByte(tag: ProtocolDescriptor): Byte =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> input.readByte()
            MinecraftNumberType.UNSIGNED -> decodeUByte().toByte()
            MinecraftNumberType.VAR -> error("Byte can only be encoded as default or unsigned")
        }

    override fun decodeTaggedShort(tag: ProtocolDescriptor): Short =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> input.readShort()
            MinecraftNumberType.UNSIGNED -> decodeUShort().toShort()
            MinecraftNumberType.VAR -> error("Short can only be encoded as default or unsigned")
        }

    override fun decodeTaggedInt(tag: ProtocolDescriptor): Int =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> input.readInt()
            MinecraftNumberType.VAR -> decodeVarInt()
            MinecraftNumberType.UNSIGNED -> decodeUInt().toInt()
        }

    override fun decodeTaggedLong(tag: ProtocolDescriptor): Long =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> input.readLong()
            MinecraftNumberType.VAR -> decodeVarLong()
            MinecraftNumberType.UNSIGNED -> decodeULong().toLong()
        }

    override fun decodeTaggedFloat(tag: ProtocolDescriptor): Float = input.readFloat()

    override fun decodeTaggedDouble(tag: ProtocolDescriptor): Double = input.readDouble()

    override fun decodeTaggedString(tag: ProtocolDescriptor): String = input.minecraft.readString(tag.stringMaxLength)

    override fun decodeTaggedEnum(tag: ProtocolDescriptor, enumDescriptor: SerialDescriptor): Int {
        val enumTag = extractEnumParameters(enumDescriptor)
        val ordinal = when (enumTag.type) {
            MinecraftEnumType.VARINT -> decodeVarInt()
            MinecraftEnumType.BYTE -> input.readByte().toInt()
            MinecraftEnumType.UNSIGNED_BYTE -> decodeUByte().toInt()
            MinecraftEnumType.INT -> input.readInt()
            MinecraftEnumType.STRING -> enumDescriptor.getElementIndex(input.minecraft.readString(enumTag.stringMaxLength))
        }

        return findEnumIndexByTag(enumDescriptor, ordinal)
    }

    override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T = deserializer.deserialize(this)

    override fun SerialDescriptor.getTag(index: Int) = extractParameters(this, index)

    override fun decodeCollectionSize(descriptor: SerialDescriptor): Int =
        when (currentTagOrNull?.arrayType) {
            MinecraftArraySizeType.VARINT -> decodeVarInt()
            MinecraftArraySizeType.READ_AVAILABLE -> input.remaining.toInt()
            else -> -1
        }

    //TODO
    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder =
        when (descriptor.kind) {
            StructureKind.CLASS -> this
            StructureKind.LIST -> {
                val size = decodeCollectionSize(descriptor)
                MinecraftProtocolDecoder(input, size)
            }
            StructureKind.MAP -> TODO()
            else -> super.beginStructure(descriptor)
        }
}
