package io.layercraft.packetlib.utils

import io.ktor.utils.io.core.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

val Input.minecraft: MinecraftProtocolDeserializeInterface<Input> get() = MinecraftByteInput(this)
val Output.minecraft: MinecraftProtocolSerializeInterface<Output> get() = MinecraftByteOutput(this)

typealias NBT = ByteArray