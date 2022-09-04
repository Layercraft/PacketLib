package io.layercraft.translator.serialization

import io.ktor.utils.io.core.*
import io.layercraft.translator.utils.minecraft
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeEncoder

open class MinecraftProtocolEncoder(output: Output) : AbstractMinecraftProtocolEncoder(output) {

    override fun shouldEncodeElementDefault(descriptor: SerialDescriptor, index: Int): Boolean = true

    override fun SerialDescriptor.getTag(index: Int): ProtocolDescriptor = extractParameters(this, index)

    override fun encodeTaggedInt(tag: ProtocolDescriptor, value: Int) =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> output.writeInt(value)
            MinecraftNumberType.UNSIGNED -> encodeUInt(value.toUInt())
            MinecraftNumberType.VAR -> encodeVarInt(value)
        }

    override fun encodeTaggedByte(tag: ProtocolDescriptor, value: Byte) =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> output.writeByte(value)
            MinecraftNumberType.UNSIGNED -> encodeUByte(value.toUByte())
            MinecraftNumberType.VAR -> error("Byte can only be encoded as default or unsigned")
        }

    override fun encodeTaggedShort(tag: ProtocolDescriptor, value: Short) =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> output.writeShort(value)
            MinecraftNumberType.UNSIGNED -> encodeUShort(value.toUShort())
            MinecraftNumberType.VAR -> error("Short can only be encoded as default or unsigned")
        }

    override fun encodeTaggedLong(tag: ProtocolDescriptor, value: Long) =
        when (tag.type) {
            MinecraftNumberType.DEFAULT -> output.writeLong(value)
            MinecraftNumberType.VAR -> encodeVarLong(value)
            MinecraftNumberType.UNSIGNED -> encodeULong(value.toULong())
        }

    override fun encodeTaggedFloat(tag: ProtocolDescriptor, value: Float) = output.writeFloat(value)

    override fun encodeTaggedDouble(tag: ProtocolDescriptor, value: Double) = output.writeDouble(value)

    override fun encodeTaggedBoolean(tag: ProtocolDescriptor, value: Boolean) = output.writeByte(if (value) 0x01 else 0x00)

    override fun encodeTaggedString(tag: ProtocolDescriptor, value: String) = output.minecraft.writeString(value, tag.stringMaxLength)

    override fun encodeTaggedEnum(tag: ProtocolDescriptor, enumDescriptor: SerialDescriptor, ordinal: Int) {
        val enumTag = extractEnumElementParameters(enumDescriptor, ordinal)
        when (extractEnumParameters(enumDescriptor).type) {
            MinecraftEnumType.VARINT -> encodeVarInt(enumTag.ordinal)
            MinecraftEnumType.BYTE -> output.writeByte(enumTag.ordinal.toByte())
            MinecraftEnumType.UNSIGNED_BYTE -> encodeUByte(enumTag.ordinal.toUByte())
            MinecraftEnumType.INT -> output.writeInt(enumTag.ordinal)
            MinecraftEnumType.STRING -> output.minecraft.writeString(enumDescriptor.getElementName(ordinal), tag.stringMaxLength)
        }
    }


    override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
        when (currentTagOrNull?.arrayType) {
            MinecraftArraySizeType.VARINT -> encodeVarInt(collectionSize)
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
