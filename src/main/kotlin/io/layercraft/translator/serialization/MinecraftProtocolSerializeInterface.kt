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

    fun writeBoolean(input: Boolean, output: Output)

    fun writeByte(input: Byte, output: Output)

    fun writeUByte(input: UByte, output: Output)

    fun writeShort(input: Short, output: Output)

    fun writeUShort(input: UShort, output: Output)

    fun writeInt(input: Int, output: Output)

    fun writeVarInt(input: Int, output: Output)

    fun writeLong(input: Long, output: Output)

    fun writeVarLong(input: Long, output: Output)

    fun writeFloat(input: Float, output: Output)
    fun writeDouble(input: Double, output: Output)

    fun writeString(input: String, n: Int = MINECRAFT_MAX_STRING_LENGTH, output: Output)
    fun writeChat(input: String, output: Output)
    fun writeIdentifier(input: String, output: Output)

    fun writeVarIntByteArray(input: ByteArray, output: Output)
    fun <T> writeVarIntArray(input: Array<T>, output: Output, encoder: (value: T, output: Output) -> Unit)

    fun writePosition(input: Position, output: Output)
    fun writeUUID(input: UUID, output: Output)
    //fun writeAngle(input: Input, output: Output): Float

    //fun writeEnum(input: Input, enum: Enum<*>, type: MinecraftEnumType, output: Output): Enum<*>
}
