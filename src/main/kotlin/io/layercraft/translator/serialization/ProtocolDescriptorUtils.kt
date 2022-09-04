package io.layercraft.translator.serialization

import io.layercraft.translator.*
import io.layercraft.translator.utils.MINECRAFT_MAX_STRING_LENGTH
import kotlinx.serialization.descriptors.SerialDescriptor

internal fun extractParameters(descriptor: SerialDescriptor, index: Int): ProtocolDescriptor {
    val format = descriptor.findElementAnnotation<MinecraftNumber>(index)?.type ?: MinecraftNumberType.DEFAULT
    val stringMaxLength = descriptor.findElementAnnotation<MinecraftString>(index)?.maxLength ?: MINECRAFT_MAX_STRING_LENGTH
    val arraySizeType = descriptor.findElementAnnotation<MinecraftArray>(index)?.sizeType ?: MinecraftArraySizeType.READ_AVAILABLE

    return ProtocolDescriptor(format, stringMaxLength, arraySizeType)
}

internal fun extractEnumParameters(descriptor: SerialDescriptor): ProtocolEnumDescriptor {
    val format = descriptor.findEntityAnnotation<MinecraftEnum>()?.type ?: MinecraftEnumType.VARINT
    val stringMaxLength = descriptor.findEntityAnnotation<MinecraftString>()?.maxLength ?: MINECRAFT_MAX_STRING_LENGTH

    return ProtocolEnumDescriptor(format, stringMaxLength)
}

internal fun extractEnumElementParameters(descriptor: SerialDescriptor, index: Int): ProtocolEnumElementDescriptor {
    val ordinal = descriptor.findElementAnnotation<SerialOrdinal>(index)?.ordinal ?: index

    return ProtocolEnumElementDescriptor(ordinal)
}

internal fun findEnumIndexByTag(descriptor: SerialDescriptor, serialOrdinal: Int): Int = (0 until descriptor.elementsCount).firstOrNull { extractEnumElementParameters(descriptor, it).ordinal == serialOrdinal } ?: -1

internal inline fun <reified A : Annotation> SerialDescriptor.findElementAnnotation(elementIndex: Int): A? = getElementAnnotations(elementIndex).find { it is A } as A?

internal inline fun <reified A : Annotation> SerialDescriptor.findEntityAnnotation(): A? = annotations.find { it is A } as A?
