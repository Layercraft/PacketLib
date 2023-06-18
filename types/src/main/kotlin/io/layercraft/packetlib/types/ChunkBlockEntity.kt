package io.layercraft.packetlib.types

data class ChunkBlockEntity(
    val chunkXz: Byte,
    val y: Short,
    val type: Int,
    val data: NBT,
)