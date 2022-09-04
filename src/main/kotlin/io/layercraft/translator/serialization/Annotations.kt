package io.layercraft.translator.serialization

import kotlinx.serialization.SerialInfo

enum class MinecraftNumberType {
    DEFAULT, UNSIGNED, VAR
}

@SerialInfo
@Target(AnnotationTarget.PROPERTY)
annotation class MinecraftNumber(
    val type: MinecraftNumberType = MinecraftNumberType.DEFAULT
)

// Supports String properties and Enum String type
@SerialInfo
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.CLASS)
annotation class MinecraftString(
    val maxLength: Int
)

@SerialInfo
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.CLASS)
annotation class Chat

enum class MinecraftEnumType {
    VARINT, BYTE, UNSIGNED_BYTE, INT, STRING
}

@SerialInfo
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
annotation class MinecraftEnum(
    val type: MinecraftEnumType = MinecraftEnumType.VARINT
)

enum class MinecraftArraySizeType {
    READ_AVAILABLE, // this will read the rest of the bytes
    VARINT
}

@SerialInfo
@Target(AnnotationTarget.PROPERTY)
annotation class MinecraftArray(
    val sizeType: MinecraftArraySizeType = MinecraftArraySizeType.VARINT
)

@SerialInfo
@Target(AnnotationTarget.PROPERTY)
annotation class SerialOrdinal(
    val ordinal: Int
)
