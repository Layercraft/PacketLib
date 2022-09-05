package io.layercraft.translator.serialization

import io.layercraft.translator.serialization.processing.MinecraftProtocolDecoder
import io.layercraft.translator.serialization.processing.MinecraftProtocolEncoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


interface MinecraftPacketSerializer<T>: KSerializer<T> {

    val deserializeUtils: MinecraftProtocolDeserializeUtils
        get() = MinecraftProtocolDeserializeUtils
    val serializeUtils: MinecraftProtocolSerializeUtils
        get() = MinecraftProtocolSerializeUtils

    override fun deserialize(decoder: Decoder): T = with(decoder as MinecraftProtocolDecoder) { deserialize(decoder) }

    override fun serialize(encoder: Encoder, value: T) = with(encoder as MinecraftProtocolEncoder) { serialize(encoder, value) }

    fun deserialize(decoder: MinecraftProtocolDecoder): T

    fun serialize(encoder: MinecraftProtocolEncoder, value: T)
}
