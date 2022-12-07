package io.layercraft.translator.utils

import io.ktor.utils.io.core.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

val Input.minecraft: MinecraftProtocolDeserializeInterface<Input> get() = MinecraftByteInput(this)
val Output.minecraft: MinecraftProtocolSerializeInterface<Output> get() = MinecraftByteOutput(this)