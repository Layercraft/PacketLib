package io.layercraft.translator.utils

import io.ktor.utils.io.core.*

val Input.mc get() = MinecraftByteInput(this)
val Output.mc get() = MinecraftByteOutput(this)
