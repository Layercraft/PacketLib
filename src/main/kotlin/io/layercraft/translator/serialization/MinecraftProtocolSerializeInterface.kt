package io.layercraft.translator.serialization

import io.ktor.utils.io.core.*
import io.layercraft.translator.types.Position
import io.layercraft.translator.utils.MINECRAFT_MAX_STRING_LENGTH
import java.util.*

/**
 * Minecraft protocol serialize interface
 *
 * @constructor Create empty Minecraft protocol serialize interface
 * @see <a href="https://wiki.vg/Protocol#Data_types">https://wiki.vg/Protocol#Data_types</a>
 */
interface MinecraftProtocolSerializeInterface {

    fun writeBoolean(input: Boolean)

    fun writeByte(input: Byte)

    fun writeUByte(input: UByte)

    fun writeShort(input: Short)

    fun writeUShort(input: UShort)

    fun writeInt(input: Int)

    fun writeVarInt(input: Int)

    fun writeLong(input: Long)

    fun writeVarLong(input: Long)

    fun writeFloat(input: Float)
    fun writeDouble(input: Double)

    fun writeString(input: String, n: Int = MINECRAFT_MAX_STRING_LENGTH)
    fun writeChat(input: String)
    fun writeIdentifier(input: String)

    fun writeVarIntByteArray(input: ByteArray)
    fun <T> writeVarIntArray(input: List<T>, encoder: (value: T, output: Output) -> Unit)

    fun writeRemainingByteArray(input: ByteArray)
    fun <T> writeRemainingArray(input: List<T>, encoder: (value: T, output: Output) -> Unit)

    fun writePosition(input: Position)
    fun writeUUID(input: UUID)
    fun writeAngle(input: Int)

    //fun writeEnum(input: Input, enum: Enum<*>, type: MinecraftEnumType): Enum<*>
}
