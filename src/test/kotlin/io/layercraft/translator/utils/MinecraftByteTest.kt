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
        packetWrite.mc.writeBoolean(true)
        packetWrite.mc.writeBoolean(false)
        
        val packetRead = packetWrite.build()
        assert(packetRead.mc.readBoolean())
        assert(!packetRead.mc.readBoolean())
    }

    @Test
    fun testByte() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeByte(0)
        packetWrite.mc.writeByte(1)
        packetWrite.mc.writeByte(127)
        packetWrite.mc.writeByte(-128)
        packetWrite.mc.writeByte(-1)
        
        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readByte(), 0.toByte())
        assertEquals(packetRead.mc.readByte(), 1.toByte())
        assertEquals(packetRead.mc.readByte(), 127.toByte())
        assertEquals(packetRead.mc.readByte(), (-128).toByte())
        assertEquals(packetRead.mc.readByte(), (-1).toByte())
    }

    @Test
    fun testUByte() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeUByte(0.toUByte())
        packetWrite.mc.writeUByte(1.toUByte())
        packetWrite.mc.writeUByte(127.toUByte())
        packetWrite.mc.writeUByte(128.toUByte())
        packetWrite.mc.writeUByte(255.toUByte())
        
        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readUByte(), 0.toUByte())
        assertEquals(packetRead.mc.readUByte(), 1.toUByte())
        assertEquals(packetRead.mc.readUByte(), 127.toUByte())
        assertEquals(packetRead.mc.readUByte(), 128.toUByte())
        assertEquals(packetRead.mc.readUByte(), 255.toUByte())
    }

    @Test
    fun testShort() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeShort(0)
        packetWrite.mc.writeShort(1)
        packetWrite.mc.writeShort(32767)
        packetWrite.mc.writeShort(-32768)
        packetWrite.mc.writeShort(-1)
        
        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readShort(), 0.toShort())
        assertEquals(packetRead.mc.readShort(), 1.toShort())
        assertEquals(packetRead.mc.readShort(), 32767.toShort())
        assertEquals(packetRead.mc.readShort(), (-32768).toShort())
        assertEquals(packetRead.mc.readShort(), (-1).toShort())
    }

    @Test
    fun testUShort() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeUShort(0.toUShort())
        packetWrite.mc.writeUShort(1.toUShort())
        packetWrite.mc.writeUShort(32767.toUShort())
        packetWrite.mc.writeUShort(32768.toUShort())
        packetWrite.mc.writeUShort(65535.toUShort())

        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readUShort(), 0.toUShort())
        assertEquals(packetRead.mc.readUShort(), 1.toUShort())
        assertEquals(packetRead.mc.readUShort(), 32767.toUShort())
        assertEquals(packetRead.mc.readUShort(), 32768.toUShort())
        assertEquals(packetRead.mc.readUShort(), 65535.toUShort())
    }

    @Test
    fun testInt() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeInt(0)
        packetWrite.mc.writeInt(1)
        packetWrite.mc.writeInt(2147483647)
        packetWrite.mc.writeInt(-2147483648)
        packetWrite.mc.writeInt(-1)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readInt(), 0)
        assertEquals(packetRead.mc.readInt(), 1)
        assertEquals(packetRead.mc.readInt(), 2147483647)
        assertEquals(packetRead.mc.readInt(), -2147483648)
        assertEquals(packetRead.mc.readInt(), -1)
    }

    @Test
    fun testVarInt() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeVarInt(0)
        packetWrite.mc.writeVarInt(1)
        packetWrite.mc.writeVarInt(2147483647)
        packetWrite.mc.writeVarInt(-2147483648)
        packetWrite.mc.writeVarInt(-1)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readVarInt(), 0)
        assertEquals(packetRead.mc.readVarInt(), 1)
        assertEquals(packetRead.mc.readVarInt(), 2147483647)
        assertEquals(packetRead.mc.readVarInt(), -2147483648)
        assertEquals(packetRead.mc.readVarInt(), -1)
    }

    @Test
    fun testLong() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeLong(0)
        packetWrite.mc.writeLong(1)
        packetWrite.mc.writeLong(92233854775807)
        packetWrite.mc.writeLong(Long.MAX_VALUE)
        packetWrite.mc.writeLong(-1)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readLong(), 0)
        assertEquals(packetRead.mc.readLong(), 1)
        assertEquals(packetRead.mc.readLong(), 92233854775807)
        assertEquals(packetRead.mc.readLong(), Long.MAX_VALUE)
        assertEquals(packetRead.mc.readLong(), -1)
    }

    @Test
    fun testVarLong() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeVarLong(0)
        packetWrite.mc.writeVarLong(1)
        packetWrite.mc.writeVarLong(92233854775807)
        packetWrite.mc.writeVarLong(Long.MAX_VALUE)
        packetWrite.mc.writeVarLong(-1)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readVarLong(), 0)
        assertEquals(packetRead.mc.readVarLong(), 1)
        assertEquals(packetRead.mc.readVarLong(), 92233854775807)
        assertEquals(packetRead.mc.readVarLong(), Long.MAX_VALUE)
        assertEquals(packetRead.mc.readVarLong(), -1)
    }

    @Test
    fun testFloat() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeFloat(0f)
        packetWrite.mc.writeFloat(1f)
        packetWrite.mc.writeFloat(1.5f)
        packetWrite.mc.writeFloat(-1.5f)
        packetWrite.mc.writeFloat(-1f)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readFloat(), 0f)
        assertEquals(packetRead.mc.readFloat(), 1f)
        assertEquals(packetRead.mc.readFloat(), 1.5f)
        assertEquals(packetRead.mc.readFloat(), -1.5f)
        assertEquals(packetRead.mc.readFloat(), -1f)
    }

    @Test
    fun testDouble() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeDouble(0.0)
        packetWrite.mc.writeDouble(1.0)
        packetWrite.mc.writeDouble(1.5)
        packetWrite.mc.writeDouble(-1.5)
        packetWrite.mc.writeDouble(-1.0)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readDouble(), 0.0)
        assertEquals(packetRead.mc.readDouble(), 1.0)
        assertEquals(packetRead.mc.readDouble(), 1.5)
        assertEquals(packetRead.mc.readDouble(), -1.5)
        assertEquals(packetRead.mc.readDouble(), -1.0)
    }

    @Test
    fun testString() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeString("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")
        packetWrite.mc.writeString("Hello World!")
        packetWrite.mc.writeString("Newspicel", 16)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readString(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")
        assertEquals(packetRead.mc.readString(), "Hello World!")
        assertEquals(packetRead.mc.readString(16), "Newspicel")
    }

    @Test
    fun testChat() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeChat("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")

        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readChat(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")
    }

    @Test
    fun testIdentifier() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeIdentifier("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")

        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readIdentifier(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")
    }

    @Test
    fun testVarIntByteArray() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeVarIntByteArray(byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))

        val packetRead = packetWrite.build()
        assertArrayEquals(packetRead.mc.readVarIntByteArray(), byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
    }

    data class TestDataType(
        val int: Int,
        val float: Float,
        val string: String
    )

    @Test
    fun testVarIntArray() {
        val data = listOf(TestDataType(1, 1f, "fghjkl"),TestDataType(2, 2f, "qwerty"),TestDataType(3, 3f, "asdfgh"))
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeVarIntArray(data) { arrayValue, arrayOutput ->
            arrayOutput.mc.writeVarInt(arrayValue.int)
            arrayOutput.mc.writeFloat(arrayValue.float)
            arrayOutput.mc.writeString(arrayValue.string)
        }

        val packetRead = packetWrite.build()
        val result = packetRead.mc.readVarIntArray { arrayInput ->
            TestDataType(
                arrayInput.mc.readVarInt(),
                arrayInput.mc.readFloat(),
                arrayInput.mc.readString()
            )
        }

        assertEquals(result, data)
    }

    @Test
    fun testRemainingArray() {
        val data = listOf(TestDataType(1, 1f, "fghjkl"),TestDataType(2, 2f, "qwerty"),TestDataType(3, 3f, "asdfgh"))
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeRemainingArray(data) { arrayValue, arrayOutput ->
                arrayOutput.mc.writeInt(arrayValue.int)
                arrayOutput.mc.writeFloat(arrayValue.float)
                arrayOutput.mc.writeString(arrayValue.string)
        }


        val packetRead = packetWrite.build()
        val result = packetRead.mc.readRemainingArray { arrayInput ->
            TestDataType(
                arrayInput.mc.readInt(),
                arrayInput.mc.readFloat(),
                arrayInput.mc.readString()
            )
        }
        assertEquals(result, data)
    }

    @Test
    fun testRemainingByteArray() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeRemainingByteArray(byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))

        val packetRead = packetWrite.build()
        assertArrayEquals(packetRead.mc.readRemainingByteArray(), byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
    }

    @Test
    fun testPosition() {
        val packetWrite = BytePacketBuilder()
        val position = Position(1, 2, 3)
        packetWrite.mc.writePosition(position)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readPosition(), position)
    }

    @Test
    fun testUUID() {
        val packetWrite = BytePacketBuilder()
        val uuid = UUID.randomUUID()
        packetWrite.mc.writeUUID(uuid)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readUUID(), uuid)
    }

    @Test
    fun testAngle() {
        val packetWrite = BytePacketBuilder()
        packetWrite.mc.writeAngle(123)

        val packetRead = packetWrite.build()
        assertEquals(packetRead.mc.readAngle(), 123)
    }
}
