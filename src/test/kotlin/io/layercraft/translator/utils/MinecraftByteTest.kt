package io.layercraft.translator.utils

import io.ktor.utils.io.core.*
import io.layercraft.translator.types.Position
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

internal class MinecraftByteTest {

    @Test
    fun testBoolean() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeBoolean(true)
        packetWrite.minecraft.writeBoolean(false)

        val packetRead = packetWrite.build()
        assert(packetRead.minecraft.readBoolean())
        assert(!packetRead.minecraft.readBoolean())
    }

    @Test
    fun testByte() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeByte(0)
        packetWrite.minecraft.writeByte(1)
        packetWrite.minecraft.writeByte(127)
        packetWrite.minecraft.writeByte(-128)
        packetWrite.minecraft.writeByte(-1)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readByte(), 0.toByte())
        assertEquals(packetRead.minecraft.readByte(), 1.toByte())
        assertEquals(packetRead.minecraft.readByte(), 127.toByte())
        assertEquals(packetRead.minecraft.readByte(), (-128).toByte())
        assertEquals(packetRead.minecraft.readByte(), (-1).toByte())
    }

    @Test
    fun testUByte() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeUByte(0.toUByte())
        packetWrite.minecraft.writeUByte(1.toUByte())
        packetWrite.minecraft.writeUByte(127.toUByte())
        packetWrite.minecraft.writeUByte(128.toUByte())
        packetWrite.minecraft.writeUByte(255.toUByte())

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readUByte(), 0.toUByte())
        assertEquals(packetRead.minecraft.readUByte(), 1.toUByte())
        assertEquals(packetRead.minecraft.readUByte(), 127.toUByte())
        assertEquals(packetRead.minecraft.readUByte(), 128.toUByte())
        assertEquals(packetRead.minecraft.readUByte(), 255.toUByte())
    }

    @Test
    fun testShort() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeShort(0)
        packetWrite.minecraft.writeShort(1)
        packetWrite.minecraft.writeShort(32767)
        packetWrite.minecraft.writeShort(-32768)
        packetWrite.minecraft.writeShort(-1)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readShort(), 0.toShort())
        assertEquals(packetRead.minecraft.readShort(), 1.toShort())
        assertEquals(packetRead.minecraft.readShort(), 32767.toShort())
        assertEquals(packetRead.minecraft.readShort(), (-32768).toShort())
        assertEquals(packetRead.minecraft.readShort(), (-1).toShort())
    }

    @Test
    fun testUShort() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeUShort(0.toUShort())
        packetWrite.minecraft.writeUShort(1.toUShort())
        packetWrite.minecraft.writeUShort(32767.toUShort())
        packetWrite.minecraft.writeUShort(32768.toUShort())
        packetWrite.minecraft.writeUShort(65535.toUShort())

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readUShort(), 0.toUShort())
        assertEquals(packetRead.minecraft.readUShort(), 1.toUShort())
        assertEquals(packetRead.minecraft.readUShort(), 32767.toUShort())
        assertEquals(packetRead.minecraft.readUShort(), 32768.toUShort())
        assertEquals(packetRead.minecraft.readUShort(), 65535.toUShort())
    }

    @Test
    fun testInt() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeInt(0)
        packetWrite.minecraft.writeInt(1)
        packetWrite.minecraft.writeInt(2147483647)
        packetWrite.minecraft.writeInt(-2147483648)
        packetWrite.minecraft.writeInt(-1)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readInt(), 0)
        assertEquals(packetRead.minecraft.readInt(), 1)
        assertEquals(packetRead.minecraft.readInt(), 2147483647)
        assertEquals(packetRead.minecraft.readInt(), -2147483648)
        assertEquals(packetRead.minecraft.readInt(), -1)
    }

    @Test
    fun testVarInt() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeVarInt(0)
        packetWrite.minecraft.writeVarInt(1)
        packetWrite.minecraft.writeVarInt(2147483647)
        packetWrite.minecraft.writeVarInt(-2147483648)
        packetWrite.minecraft.writeVarInt(-1)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readVarInt(), 0)
        assertEquals(packetRead.minecraft.readVarInt(), 1)
        assertEquals(packetRead.minecraft.readVarInt(), 2147483647)
        assertEquals(packetRead.minecraft.readVarInt(), -2147483648)
        assertEquals(packetRead.minecraft.readVarInt(), -1)
    }

    @Test
    fun testLong() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeLong(0)
        packetWrite.minecraft.writeLong(1)
        packetWrite.minecraft.writeLong(92233854775807)
        packetWrite.minecraft.writeLong(Long.MAX_VALUE)
        packetWrite.minecraft.writeLong(-1)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readLong(), 0)
        assertEquals(packetRead.minecraft.readLong(), 1)
        assertEquals(packetRead.minecraft.readLong(), 92233854775807)
        assertEquals(packetRead.minecraft.readLong(), Long.MAX_VALUE)
        assertEquals(packetRead.minecraft.readLong(), -1)
    }

    @Test
    fun testVarLong() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeVarLong(0)
        packetWrite.minecraft.writeVarLong(1)
        packetWrite.minecraft.writeVarLong(92233854775807)
        packetWrite.minecraft.writeVarLong(Long.MAX_VALUE)
        packetWrite.minecraft.writeVarLong(-1)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readVarLong(), 0)
        assertEquals(packetRead.minecraft.readVarLong(), 1)
        assertEquals(packetRead.minecraft.readVarLong(), 92233854775807)
        assertEquals(packetRead.minecraft.readVarLong(), Long.MAX_VALUE)
        assertEquals(packetRead.minecraft.readVarLong(), -1)
    }

    @Test
    fun testFloat() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeFloat(0f)
        packetWrite.minecraft.writeFloat(1f)
        packetWrite.minecraft.writeFloat(1.5f)
        packetWrite.minecraft.writeFloat(-1.5f)
        packetWrite.minecraft.writeFloat(-1f)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readFloat(), 0f)
        assertEquals(packetRead.minecraft.readFloat(), 1f)
        assertEquals(packetRead.minecraft.readFloat(), 1.5f)
        assertEquals(packetRead.minecraft.readFloat(), -1.5f)
        assertEquals(packetRead.minecraft.readFloat(), -1f)
    }

    @Test
    fun testDouble() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeDouble(0.0)
        packetWrite.minecraft.writeDouble(1.0)
        packetWrite.minecraft.writeDouble(1.5)
        packetWrite.minecraft.writeDouble(-1.5)
        packetWrite.minecraft.writeDouble(-1.0)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readDouble(), 0.0)
        assertEquals(packetRead.minecraft.readDouble(), 1.0)
        assertEquals(packetRead.minecraft.readDouble(), 1.5)
        assertEquals(packetRead.minecraft.readDouble(), -1.5)
        assertEquals(packetRead.minecraft.readDouble(), -1.0)
    }

    @Test
    fun testString() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeString("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")
        packetWrite.minecraft.writeString("Hello World!")
        packetWrite.minecraft.writeString("Newspicel", 16)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readString(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")
        assertEquals(packetRead.minecraft.readString(), "Hello World!")
        assertEquals(packetRead.minecraft.readString(16), "Newspicel")
    }

    @Test
    fun testChat() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeChat("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readChat(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")
    }

    @Test
    fun testIdentifier() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeIdentifier("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readIdentifier(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")
    }

    @Test
    fun testVarIntByteArray() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeVarIntByteArray(byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))

        val packetRead = packetWrite.build()
        assertArrayEquals(packetRead.minecraft.readVarIntByteArray(), byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
    }

    data class TestDataType(
        val int: Int,
        val float: Float,
        val string: String,
    )

    @Test
    fun testVarIntArray() {
        val data = listOf(TestDataType(1, 1f, "fghjkl"), TestDataType(2, 2f, "qwerty"), TestDataType(3, 3f, "asdfgh"))
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeVarIntArray(data) { arrayValue, arrayOutput ->
            arrayOutput.writeVarInt(arrayValue.int)
            arrayOutput.writeFloat(arrayValue.float)
            arrayOutput.writeString(arrayValue.string)
        }

        val packetRead = packetWrite.build()
        val result = packetRead.minecraft.readVarIntArray { arrayInput ->
            TestDataType(
                arrayInput.readVarInt(),
                arrayInput.readFloat(),
                arrayInput.readString(),
            )
        }

        assertEquals(result, data)
    }

    @Test
    fun testRemainingArray() {
        val data = listOf(TestDataType(1, 1f, "fghjkl"), TestDataType(2, 2f, "qwerty"), TestDataType(3, 3f, "asdfgh"))
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeRemainingArray(data) { arrayValue, arrayOutput ->
            arrayOutput.writeInt(arrayValue.int)
            arrayOutput.writeFloat(arrayValue.float)
            arrayOutput.writeString(arrayValue.string)
        }

        val packetRead = packetWrite.build()
        val result = packetRead.minecraft.readRemainingArray { arrayInput ->
            TestDataType(
                arrayInput.readInt(),
                arrayInput.readFloat(),
                arrayInput.readString(),
            )
        }
        assertEquals(result, data)
    }

    @Test
    fun testRemainingByteArray() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeRemainingByteArray(byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))

        val packetRead = packetWrite.build()
        assertArrayEquals(packetRead.minecraft.readRemainingByteArray(), byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
    }

    @Test
    fun testPosition() {
        val packetWrite = BytePacketBuilder()
        val position = Position(1, 2, 3)
        packetWrite.minecraft.writePosition(position)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readPosition(), position)
    }

    @Test
    fun testUUID() {
        val packetWrite = BytePacketBuilder()
        val uuid = UUID.randomUUID()
        packetWrite.minecraft.writeUUID(uuid)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.minecraft.readUUID(), uuid)
    }

    @Test
    fun testAngle() {
        val packetWrite = BytePacketBuilder()
        packetWrite.minecraft.writeAngle(123f)

        val packetRead = packetWrite.build()
        // assertEquals(packetRead.minecraft.readAngle(), 123f)
    }
}