package io.layercraft.translator.serialization

data class ProtocolDescriptor(
    val type: MinecraftNumberType,
    val stringMaxLength: Int,
    val arrayType: MinecraftArraySizeType,
)

data class ProtocolEnumDescriptor(
    val type: MinecraftEnumType,
    val stringMaxLength: Int
)

data class ProtocolEnumElementDescriptor(
    val ordinal: Int
)
