package io.layercraft.translator.serialization

import io.ktor.utils.io.core.*
import io.layercraft.translator.types.Position
import io.layercraft.translator.utils.MINECRAFT_MAX_CHAT_LENGTH
import io.layercraft.translator.utils.MINECRAFT_MAX_IDENTIFIER_LENGTH
import io.layercraft.translator.utils.MINECRAFT_MAX_STRING_LENGTH
import io.layercraft.translator.utils.MinecraftStringUtils
import io.layercraft.translator.utils.MinecraftVarIntUtils
import io.layercraft.translator.utils.MinecraftVarLongUtils
import java.util.*

/**
 * Minecraft protocol serialize interface
 *
 * @constructor Create empty Minecraft protocol serialize interface
 * @see <a href="https://wiki.vg/Protocol#Data_types">https://wiki.vg/Protocol#Data_types</a>
 */
interface MinecraftProtocolSerializeInterface<O> {

    val output: O

    fun writeByte(input: Byte)
    fun writeBytes(input: ByteArray)


    fun writeBoolean(input: Boolean)
    fun writeInt(input: Int)
    fun writeLong(input: Long)
    fun writeShort(input: Short)
    fun writeFloat(input: Float)
    fun writeDouble(input: Double)


    fun writeUByte(input: UByte)
    fun writeUShort(input: UShort)

    fun writeVarInt(input: Int) = MinecraftVarIntUtils.writeVarInt(input, this)
    fun writeVarLong(input: Long) = MinecraftVarLongUtils.writeVarLong(input, this)


    fun writeString(input: String, n: Int = MINECRAFT_MAX_STRING_LENGTH) = MinecraftStringUtils.writeString(n, input, this)
    fun writeChat(input: String) = writeString(input, MINECRAFT_MAX_CHAT_LENGTH)
    fun writeIdentifier(input: String) = writeString(input, MINECRAFT_MAX_IDENTIFIER_LENGTH)

    fun writeVarIntByteArray(input: ByteArray) {
        writeVarInt(input.size)
        writeBytes(input)
    }
    fun <T> writeVarIntArray(input: List<T>, encoder: (value: T, output: MinecraftProtocolSerializeInterface<O>) -> Unit) {
        writeVarInt(input.size)
        input.forEach { encoder(it, this) }
    }

    fun writeRemainingByteArray(input: ByteArray) = writeBytes(input)
    fun <T> writeRemainingArray(input: List<T>, encoder: (value: T, output: MinecraftProtocolSerializeInterface<O>) -> Unit) = input.forEach { encoder(it, this) }

    fun writePosition(input: Position) = writeLong(Position.positionToLong(input))
    fun writeUUID(input: UUID) {
        writeLong(input.mostSignificantBits)
        writeLong(input.leastSignificantBits)
    }
    fun writeAngle(input: Float) = writeByte((input * 256f / 360f).toInt().toByte())
}
