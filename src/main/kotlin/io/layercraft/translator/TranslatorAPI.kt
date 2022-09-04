package io.layercraft.translator

import io.ktor.utils.io.core.*
import io.layercraft.translator.serialization.MinecraftProtocolDecoder
import io.layercraft.translator.serialization.MinecraftProtocolEncoder
import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

@Suppress("MemberVisibilityCanBePrivate")
class TranslatorAPI (
    override val serializersModule: SerializersModule = EmptySerializersModule()
) : BinaryFormat {

    companion object Default : BinaryFormat by TranslatorAPI()

    override fun <T> decodeFromByteArray(deserializer: DeserializationStrategy<T>, bytes: ByteArray): T {
        val packetRead = ByteReadPacket(bytes)

        return decodeFromInput(deserializer, packetRead)
    }

    override fun <T> encodeToByteArray(serializer: SerializationStrategy<T>, value: T): ByteArray {
        val packetWrite = BytePacketBuilder()

        encodeToOutput(serializer, value, packetWrite)

        return packetWrite.build().readBytes()
    }

    fun <T> decodeFromInput(deserializer: DeserializationStrategy<T>, input: Input): T {
        val decoder = MinecraftProtocolDecoder(input)
        return decoder.decodeSerializableValue(deserializer)
    }

    fun <T> encodeToOutput(serializer: SerializationStrategy<T>, value: T, output: Output) {
        val encoder = MinecraftProtocolEncoder(output)
        encoder.encodeSerializableValue(serializer, value)
    }
}
