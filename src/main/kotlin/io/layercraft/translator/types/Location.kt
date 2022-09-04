package io.layercraft.translator.types


import io.layercraft.translator.MinecraftNumber
import io.layercraft.translator.MinecraftNumberType
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val x: Double,
    val y: Double,
    val z: Double,

    @MinecraftNumber(MinecraftNumberType.UNSIGNED)
    val pitch: Byte, // Angle: Unsigned Byte 1-256

    @MinecraftNumber(MinecraftNumberType.UNSIGNED)
    val yaw: Byte, // Angle: Unsigned Byte 1-256
)
