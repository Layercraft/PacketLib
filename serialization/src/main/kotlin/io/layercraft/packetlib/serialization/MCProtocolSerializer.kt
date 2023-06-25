package io.layercraft.packetlib.serialization

import io.layercraft.packetlib.serialization.types.*
import io.layercraft.packetlib.types.ChunkBlockEntity
import io.layercraft.packetlib.types.NBT
import io.layercraft.packetlib.types.Position
import java.util.*

/**
 * Minecraft protocol serialize interface
 *
 * @constructor Create empty Minecraft protocol serialize interface
 * @see <a href="https://wiki.vg/Protocol#Data_types">https://wiki.vg/Protocol#Data_types</a>
 */
interface MCProtocolSerializer<O> {

    val output: O

    fun writeByte(input: Byte)
    fun writeBytes(input: ByteArray)

    fun writeBoolean(input: Boolean) = writeByte(if (input) 1 else 0)
    fun writeInt(input: Int)
    fun writeLong(input: Long)
    fun writeShort(input: Short)
    fun writeFloat(input: Float)
    fun writeDouble(input: Double)

    fun writeUByte(input: UByte) = writeByte(input.toByte())
    fun writeUShort(input: UShort) = writeShort(input.toShort())
    fun writeUInt(input: UInt) = writeInt(input.toInt())
    fun writeULong(input: ULong) = writeLong(input.toLong())

    fun writeVarInt(input: Int) = VarIntSerializer.writeVarInt(input, this)
    fun writeVarLong(input: Long) = VarLongSerializer.writeVarLong(input, this)

    fun writeString(input: String, n: Int = MINECRAFT_MAX_STRING_LENGTH) = VarStringSerializer.writeString(n, input, this)
    fun writeChat(input: String) = writeString(input, MINECRAFT_MAX_CHAT_LENGTH)
    fun writeIdentifier(input: String) = writeString(input, MINECRAFT_MAX_IDENTIFIER_LENGTH)

    fun writeVarIntByteArray(input: ByteArray) {
        writeVarInt(input.size)
        writeBytes(input)
    }
    fun <T> writeVarIntArray(input: List<T>, encoder: (value: T, output: MCProtocolSerializer<O>) -> Unit) {
        writeVarInt(input.size)
        input.forEach { encoder(it, this) }
    }

    fun writeRemainingByteArray(input: ByteArray) = writeBytes(input)
    fun <T> writeRemainingArray(input: List<T>, encoder: (value: T, output: MCProtocolSerializer<O>) -> Unit) = input.forEach { encoder(it, this) }

    fun writePosition(input: Position) = writeLong(PositionSerializer.positionToLong(input))
    fun writeUUID(input: UUID) {
        writeLong(input.mostSignificantBits)
        writeLong(input.leastSignificantBits)
    }
    fun writeAngle(input: Float) = writeByte((input * 256f / 360f).toInt().toByte())

    fun writeNbt(input: NBT) = writeBytes(input)

    fun writeChunkBlockEntity(input: ChunkBlockEntity) = ChunkBlockEntitySerializer.write(input, this)
}