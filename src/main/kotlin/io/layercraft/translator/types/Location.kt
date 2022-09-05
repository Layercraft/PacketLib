package io.layercraft.translator.types

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val x: Double,
    val y: Double,
    val z: Double,

    val pitch: UByte, // Angle: Unsigned Byte 1-256

    val yaw: UByte, // Angle: Unsigned Byte 1-256
)
