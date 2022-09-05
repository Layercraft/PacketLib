package io.layercraft.translator.serialization.processing

import io.ktor.utils.io.core.*
import io.layercraft.translator.types.Position
import io.layercraft.translator.utils.minecraft
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeEncoder
import java.util.*
import io.layercraft.translator.serialization.MinecraftProtocolSerializeUtils as Utils

open class MinecraftProtocolEncoder(output: Output) : AbstractMinecraftProtocolEncoder(output) {

    override fun shouldEncodeElementDefault(descriptor: SerialDescriptor, index: Int): Boolean = true

    override fun SerialDescriptor.getTag(index: Int): ProtocolDescriptor = extractParameters(this, index)

    fun encodeValue(tag: ProtocolDescriptor = popTag(), value: Any, output: Output) =
        when (value) {
            is Boolean -> encodeTaggedBoolean(tag, value)
            is Byte -> encodeTaggedByte(tag, value)
            is UByte -> encodeTaggedUByte(value)
            is Short -> encodeTaggedShort(tag, value)
            is UShort -> encodeTaggedUShort(value)
            is Int -> encodeTaggedInt(tag, value)
            is Long -> encodeTaggedLong(tag, value)
            is Float -> encodeTaggedFloat(tag, value)
            is Double -> encodeTaggedDouble(tag, value)
            is String -> encodeTaggedString(tag, value)
            is ByteArray -> if (tag.arrayType == MinecraftArraySizeType.VARINT) encodeTaggedVarIntByteArray(value) else error("Unsupported array type: ${tag.arrayType}")
            is UUID -> encodeTaggedUUID(value)
            is Position -> encodeTaggedPosition(value)
            else -> error("Unsupported type: ${value::class}")
        }

    public override fun encodeTaggedValue(tag: ProtocolDescriptor, value: Any) = encodeValue(tag, value, output)

    public override fun encodeTaggedInt(tag: ProtocolDescriptor, value: Int) =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> Utils.writeInt(value, output)
            MinecraftNumberType.VAR -> Utils.writeVarInt(value, output)
        }

    public override fun encodeTaggedByte(tag: ProtocolDescriptor, value: Byte) = Utils.writeByte(value, output)

    public override fun encodeTaggedShort(tag: ProtocolDescriptor, value: Short) = Utils.writeShort(value, output)

    public override fun encodeTaggedLong(tag: ProtocolDescriptor, value: Long) =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> Utils.writeLong(value, output)
            MinecraftNumberType.VAR -> Utils.writeVarLong(value, output)
        }

    public override fun encodeTaggedFloat(tag: ProtocolDescriptor, value: Float) = Utils.writeFloat(value, output)

    public override fun encodeTaggedDouble(tag: ProtocolDescriptor, value: Double) = Utils.writeDouble(value, output)
    public override fun encodeTaggedBoolean(tag: ProtocolDescriptor, value: Boolean) = Utils.writeBoolean(value, output)

    public override fun encodeTaggedString(tag: ProtocolDescriptor, value: String) = Utils.writeString(value, tag.stringMaxLength, output)

    override fun encodeTaggedEnum(tag: ProtocolDescriptor, enumDescriptor: SerialDescriptor, ordinal: Int) {
        val enumTag = extractEnumElementParameters(enumDescriptor, ordinal)
        when (extractEnumParameters(enumDescriptor).type) {
            MinecraftEnumType.VARINT -> Utils.writeVarInt(enumTag.ordinal, output)
            MinecraftEnumType.BYTE -> Utils.writeByte(enumTag.ordinal.toByte(), output)
            MinecraftEnumType.UNSIGNED_BYTE -> Utils.writeUByte(enumTag.ordinal.toUByte(), output)
            MinecraftEnumType.INT -> Utils.writeInt(enumTag.ordinal, output)
            MinecraftEnumType.STRING -> output.minecraft.writeString(enumDescriptor.getElementName(ordinal), tag.stringMaxLength)
        }
    }


    override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
        when (currentTagOrNull?.arrayType) {
            MinecraftArraySizeType.VARINT -> Utils.writeVarInt(collectionSize, output)
            MinecraftArraySizeType.READ_AVAILABLE -> {}
            else -> {}
        }
        return this
    }

    //TODO
    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder =
        when (descriptor.kind) {
            StructureKind.CLASS -> this
            StructureKind.LIST -> this
            StructureKind.MAP -> TODO()
            else -> super.beginStructure(descriptor)
        }
}
