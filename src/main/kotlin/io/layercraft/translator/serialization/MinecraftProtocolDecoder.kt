package io.layercraft.translator.serialization

import io.ktor.utils.io.core.*
import io.layercraft.translator.MinecraftEnumType
import io.layercraft.translator.MinecraftNumberType
import io.layercraft.translator.exceptions.MinecraftProtocolDecodingException
import io.layercraft.translator.utils.minecraft
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder

open class MinecraftProtocolDecoder(input: Input) : AbstractMinecraftProtocolDecoder(input) {
    private var currentIndex = 0

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        return if (descriptor.elementsCount == currentIndex)
            DECODE_DONE
        else currentIndex++
    }

    override fun decodeTaggedBoolean(tag: ProtocolDesc): Boolean =
        when (val i = input.readByte()) {
            0x00.toByte() -> false
            0x01.toByte() -> true
            else -> throw MinecraftProtocolDecodingException("Expected boolean value (0 or 1), found $i")
        }

    override fun decodeTaggedByte(tag: ProtocolDesc): Byte =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> input.readByte()
            MinecraftNumberType.UNSIGNED -> decodeUByte().toByte()
            MinecraftNumberType.VAR -> error("Byte can only be encoded as default or unsigned")
        }

    override fun decodeTaggedShort(tag: ProtocolDesc): Short =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> input.readShort()
            MinecraftNumberType.UNSIGNED -> decodeUShort().toShort()
            MinecraftNumberType.VAR -> error("Short can only be encoded as default or unsigned")
        }

    override fun decodeTaggedInt(tag: ProtocolDesc): Int =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> input.readInt()
            MinecraftNumberType.VAR -> decodeVarInt()
            MinecraftNumberType.UNSIGNED -> decodeUInt().toInt()
        }

    override fun decodeTaggedLong(tag: ProtocolDesc): Long =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> input.readLong()
            MinecraftNumberType.VAR -> decodeVarLong()
            MinecraftNumberType.UNSIGNED -> decodeULong().toLong()
        }

    override fun decodeTaggedFloat(tag: ProtocolDesc): Float = input.readFloat()

    override fun decodeTaggedDouble(tag: ProtocolDesc): Double = input.readDouble()

    override fun decodeTaggedString(tag: ProtocolDesc): String = input.minecraft.readString(tag.stringMaxLength)

    override fun decodeTaggedEnum(tag: ProtocolDesc, enumDescriptor: SerialDescriptor): Int {
        val enumTag = extractEnumParameters(enumDescriptor)
        val ordinal = when (enumTag.type) {
            MinecraftEnumType.VARINT -> decodeVarInt()
            MinecraftEnumType.BYTE -> input.readByte().toInt()
            MinecraftEnumType.UNSIGNED_BYTE -> decodeUByte().toInt()
            MinecraftEnumType.INT -> input.readInt()
            MinecraftEnumType.STRING ->
                enumDescriptor.getElementIndex(input.minecraft.readString(enumTag.stringMaxLength))
        }

        return findEnumIndexByTag(enumDescriptor, ordinal)
    }

    override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T = deserializer.deserialize(this)

    override fun decodeCollectionSize(descriptor: SerialDescriptor): Int {
        currentTag.type
        return super.decodeCollectionSize(descriptor)
    }

    override fun SerialDescriptor.getTag(index: Int) = extractParameters(this, index)

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder =
        when (descriptor.kind) {
            is StructureKind.CLASS -> {
                // TODO
                MinecraftProtocolDecoder(input)
            }

            is StructureKind.LIST -> {
                // TODO
                super.beginStructure(descriptor)
            }

            else -> {
                super.beginStructure(descriptor)
            }
        }
}
