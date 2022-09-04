package io.layercraft.translator.serialization


import io.layercraft.translator.MinecraftArraySizeType
import io.layercraft.translator.MinecraftEnumType
import io.layercraft.translator.MinecraftNumberType

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
