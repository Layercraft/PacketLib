package io.layercraft.translator.serialization


import io.layercraft.translator.MinecraftArraySizeType
import io.layercraft.translator.MinecraftEnumType
import io.layercraft.translator.MinecraftNumberType

data class ProtocolDesc(
    val type: MinecraftNumberType,
    val stringMaxLength: Int,
    val arrayType: MinecraftArraySizeType,
)

data class ProtocolEnumDesc(
    val type: MinecraftEnumType,
    val stringMaxLength: Int
)

data class ProtocolEnumElementDesc(
    val ordinal: Int
)
