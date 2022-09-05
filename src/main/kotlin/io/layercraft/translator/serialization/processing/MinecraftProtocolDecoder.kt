package io.layercraft.translator.serialization.processing

import io.ktor.utils.io.core.*
import io.layercraft.translator.types.Position
import io.layercraft.translator.utils.minecraft
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import java.util.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeUtils as Utils

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

    internal inline fun <reified T : Any> decodeValue(tag: ProtocolDescriptor, input: Input): T =
        when (T::class) {
            Boolean::class -> decodeTaggedBoolean(tag) as T
            Byte::class -> decodeTaggedByte(tag) as T
            UByte::class -> decodeTaggedUByte() as T
            Short::class -> decodeTaggedShort(tag) as T
            UShort::class -> decodeTaggedUShort() as T
            Int::class -> decodeTaggedInt(tag) as T
            Long::class -> decodeTaggedLong(tag) as T
            Float::class -> decodeTaggedFloat(tag) as T
            Double::class -> decodeTaggedDouble(tag) as T
            String::class -> decodeTaggedString(tag) as T
            ByteArray::class -> if (tag.type == MinecraftNumberType.VAR) Utils.readVarIntByteArray(input) as T else error("ByteArray can only be decoded with MinecraftNumberType.VAR")
            UUID::class -> decodeTaggedUUID() as T
            Position::class -> decodeTaggedPosition() as T
            else -> error("Unsupported type ${T::class}")
        }

    override fun decodeTaggedValue(tag: ProtocolDescriptor): Any = error("Can not decode value without type information")

    public override fun decodeTaggedBoolean(tag: ProtocolDescriptor): Boolean = Utils.readBoolean(input)

    public override fun decodeTaggedByte(tag: ProtocolDescriptor): Byte = Utils.readByte(input)

    public override fun decodeTaggedShort(tag: ProtocolDescriptor): Short = Utils.readShort(input)

    public override fun decodeTaggedInt(tag: ProtocolDescriptor): Int =
        when (tag.type) {
            MinecraftNumberType.VAR -> Utils.readVarInt(input)
            MinecraftNumberType.DEFAULT -> Utils.readInt(input)
        }

    public override fun decodeTaggedLong(tag: ProtocolDescriptor): Long =
        when (tag.type) {
            MinecraftNumberType.VAR -> Utils.readVarLong(input)
            MinecraftNumberType.DEFAULT -> Utils.readLong(input)
        }

    public override fun decodeTaggedFloat(tag: ProtocolDescriptor): Float = Utils.readFloat(input)

    public override fun decodeTaggedDouble(tag: ProtocolDescriptor): Double = Utils.readDouble(input)

    public override fun decodeTaggedString(tag: ProtocolDescriptor): String = Utils.readString(input, tag.stringMaxLength)

    public override fun decodeTaggedEnum(tag: ProtocolDescriptor, enumDescriptor: SerialDescriptor): Int {
        val enumTag = extractEnumParameters(enumDescriptor)
        val ordinal = when (enumTag.type) {
            MinecraftEnumType.VARINT -> Utils.readVarInt(input)
            MinecraftEnumType.BYTE -> Utils.readByte(input).toInt()
            MinecraftEnumType.UNSIGNED_BYTE -> Utils.readUByte(input).toInt()
            MinecraftEnumType.INT -> Utils.readInt(input)
            MinecraftEnumType.STRING -> enumDescriptor.getElementIndex(input.minecraft.readString(enumTag.stringMaxLength))
        }

        return findEnumIndexByTag(enumDescriptor, ordinal)
    }

    override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T = deserializer.deserialize(this)

    override fun SerialDescriptor.getTag(index: Int) = extractParameters(this, index)

    override fun decodeCollectionSize(descriptor: SerialDescriptor): Int =
        when (currentTagOrNull?.arrayType) {
            MinecraftArraySizeType.VARINT -> Utils.readVarInt(input)
            MinecraftArraySizeType.READ_AVAILABLE -> input.remaining.toInt()
            else -> -1
        }

    //TODO
    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder =
        when (descriptor.kind) {
            StructureKind.CLASS -> this
            StructureKind.LIST -> MinecraftProtocolDecoder(input, decodeCollectionSize(descriptor))
            StructureKind.MAP -> TODO()
            else -> super.beginStructure(descriptor)
        }
}
