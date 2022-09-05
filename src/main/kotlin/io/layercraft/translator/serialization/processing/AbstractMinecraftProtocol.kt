package io.layercraft.translator.serialization.processing

import io.ktor.utils.io.core.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeUtils
import io.layercraft.translator.serialization.MinecraftProtocolSerializeUtils
import io.layercraft.translator.types.Position
import io.layercraft.translator.utils.MINECRAFT_MAX_STRING_LENGTH
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.internal.TaggedDecoder
import kotlinx.serialization.internal.TaggedEncoder
import java.util.*

@OptIn(InternalSerializationApi::class)
abstract class AbstractMinecraftProtocolDecoder(protected val input: Input) : TaggedDecoder<ProtocolDescriptor>(){
    fun decodeTaggedUByte(): UByte = MinecraftProtocolDeserializeUtils.readUByte(input)
    fun decodeTaggedUShort(): UShort = MinecraftProtocolDeserializeUtils.readUShort(input)

    fun decodeTaggedVarInt(): Int = MinecraftProtocolDeserializeUtils.readVarInt(input)
    fun decodeTaggedVarLong(): Long = MinecraftProtocolDeserializeUtils.readVarLong(input)

    fun decodeTaggedString(n: Int = MINECRAFT_MAX_STRING_LENGTH): String = MinecraftProtocolDeserializeUtils.readString(input, n)
    fun decodeTaggedChat(): String = MinecraftProtocolDeserializeUtils.readChat(input)
    fun decodeTaggedIdentifier(): String = MinecraftProtocolDeserializeUtils.readIdentifier(input)

    fun decodeTaggedUUID(): UUID = MinecraftProtocolDeserializeUtils.readUUID(input)
    fun decodeTaggedPosition(): Position = MinecraftProtocolDeserializeUtils.readPosition(input)

    fun decodeTaggedVarIntByteArray(): ByteArray = MinecraftProtocolDeserializeUtils.readVarIntByteArray(input)
}

@OptIn(InternalSerializationApi::class)
abstract class AbstractMinecraftProtocolEncoder(protected val output: Output) : TaggedEncoder<ProtocolDescriptor>(){
    fun encodeTaggedUByte(value: UByte) = MinecraftProtocolSerializeUtils.writeUByte(value, output)
    fun encodeTaggedUShort(value: UShort) = MinecraftProtocolSerializeUtils.writeUShort(value, output)

    fun encodeTaggedVarInt(value: Int) = MinecraftProtocolSerializeUtils.writeVarInt(value, output)
    fun encodeTaggedVarLong(value: Long) = MinecraftProtocolSerializeUtils.writeVarLong(value, output)

    fun encodeTaggedString(value: String, n: Int = MINECRAFT_MAX_STRING_LENGTH) = MinecraftProtocolSerializeUtils.writeString(value, n, output)
    fun encodeTaggedChat(value: String) = MinecraftProtocolSerializeUtils.writeChat(value, output)
    fun encodeTaggedIdentifier(value: String) = MinecraftProtocolSerializeUtils.writeIdentifier(value, output)

    fun encodeTaggedUUID(value: UUID) = MinecraftProtocolSerializeUtils.writeUUID(value, output)
    fun encodeTaggedPosition(value: Position) = MinecraftProtocolSerializeUtils.writePosition(value, output)

    fun encodeTaggedVarIntByteArray(value: ByteArray) = MinecraftProtocolSerializeUtils.writeVarIntByteArray(value, output)
}
