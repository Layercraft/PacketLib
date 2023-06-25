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
interface MCProtocolDeserializer<I> {

    val input: I

    val remaining: Int

    fun readByte(): Byte
    fun readBytes(): ByteArray = readBytes(remaining)
    fun readBytes(n: Int): ByteArray

    fun readBoolean(): Boolean = readByte() != 0.toByte()
    fun readInt(): Int
    fun readLong(): Long
    fun readShort(): Short
    fun readFloat(): Float
    fun readDouble(): Double

    fun readUByte(): UByte = readByte().toUByte()
    fun readUShort(): UShort = readShort().toUShort()
    fun readUInt(): UInt = readInt().toUInt()
    fun readULong(): ULong = readLong().toULong()

    fun readVarInt(): Int = VarIntSerializer.readVarInt(this)
    fun readVarLong(): Long = VarLongSerializer.readVarLong(this)

    fun readString(n: Int = MINECRAFT_MAX_STRING_LENGTH): String = VarStringSerializer.readString(n, this)
    fun readChat(): String = readString(MINECRAFT_MAX_CHAT_LENGTH)
    fun readIdentifier(): String = readString(MINECRAFT_MAX_IDENTIFIER_LENGTH)

    fun readUTF8String(): String = readBytes(readShort().toInt()).toString()

    fun readVarIntByteArray(): ByteArray = readBytes(readVarInt())
    fun <T> readVarIntArray(decoder: (input: MCProtocolDeserializer<I>) -> T): List<T> =
        (1..readVarInt()).map { decoder(this) }.toList()

    fun readRemainingByteArray(): ByteArray = readBytes()
    fun <T> readRemainingArray(decoder: (input: MCProtocolDeserializer<I>) -> T): List<T> {
        val list: MutableList<T> = mutableListOf()

        while (remaining > 0) {
            list.add(decoder(this))
        }

        return list.toList()
    }

    fun readPosition(): Position = PositionSerializer.longToPosition(readLong())
    fun readUUID(): UUID = UUID(readLong(), readLong())
    fun readAngle(): Float = (readByte() * 360.0f / 256f)

    fun readNbt(): NBT {
        val list: MutableList<Byte> = mutableListOf()
        while (true) {
            val type = readByte()
            list.add(type)
            if (type == 0.toByte()) {
                break
            }
        }
        return list.toByteArray()
    }

    fun readChunkBlockEntity(): ChunkBlockEntity = ChunkBlockEntitySerializer.read(this)
}