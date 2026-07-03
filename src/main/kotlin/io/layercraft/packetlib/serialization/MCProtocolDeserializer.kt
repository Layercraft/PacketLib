package io.layercraft.packetlib.serialization

import io.layercraft.packetlib.types.*
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
    fun readBytes(): ByteArray
    fun readBytes(n: Int): ByteArray

    fun readBoolean(): Boolean
    fun readInt(): Int
    fun readLong(): Long
    fun readShort(): Short
    fun readFloat(): Float
    fun readDouble(): Double

    fun readUByte(): UByte
    fun readUShort(): UShort
    fun readUInt(): UInt = readInt().toUInt()
    fun readULong(): ULong = readLong().toULong()

    fun readVarInt(): Int = MinecraftVarIntUtils.readVarInt(this)
    fun readVarLong(): Long = MinecraftVarLongUtils.readVarLong(this)

    fun readString(n: Int = MINECRAFT_MAX_STRING_LENGTH): String = MinecraftStringUtils.readString(n, this)
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

    fun readPosition(): Position = Position.longToPosition(readLong())

    /** Position encoding used before 1.14. */
    fun readLegacyPosition(): Position = Position.longToLegacyPosition(readLong())
    fun readUUID(): UUID = UUID(readLong(), readLong())
    fun readAngle(): Float = (readByte() * 360.0f / 256f)

    fun readNbt(): NBT = NbtCodec.readNamed(this)
    fun readOptionalNbt(): NBT? = NbtCodec.readOptionalNamed(this)

    /** Network NBT without a root tag name (1.20.2+). */
    fun readAnonymousNbt(): NBT = NbtCodec.readAnonymous(this)
    fun readAnonymousOptionalNbt(): NBT? = NbtCodec.readOptionalAnonymous(this)

    fun readChunkBlockEntity(): ChunkBlockEntity = ChunkBlockEntity.read(this)
}