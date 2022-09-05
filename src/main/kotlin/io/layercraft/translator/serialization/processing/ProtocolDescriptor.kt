package io.layercraft.translator.serialization.processing

data class ProtocolDescriptor(
    val type: MinecraftNumberType,
    val stringMaxLength: Int,
    val arrayType: MinecraftArraySizeType,
){
    companion object {
        val DEFAULT = ProtocolDescriptor(MinecraftNumberType.DEFAULT, 32767, MinecraftArraySizeType.VARINT)
        val VARINT = ProtocolDescriptor(MinecraftNumberType.VAR, 32767, MinecraftArraySizeType.VARINT)
    }
}

data class ProtocolEnumDescriptor(
    val type: MinecraftEnumType,
    val stringMaxLength: Int
)

data class ProtocolEnumElementDescriptor(
    val ordinal: Int
)
