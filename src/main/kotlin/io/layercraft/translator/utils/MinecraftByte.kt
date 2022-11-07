package io.layercraft.translator.utils

import io.ktor.utils.io.core.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

val Input.mc: MinecraftProtocolDeserializeInterface get() = MinecraftByteInput(this)
val Output.mc: MinecraftProtocolSerializeInterface get() = MinecraftByteOutput(this)
