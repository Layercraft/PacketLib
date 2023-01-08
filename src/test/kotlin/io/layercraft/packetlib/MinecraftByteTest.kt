package io.layercraft.packetlib

import io.layercraft.packetlib.types.Position
import io.layercraft.packetlib.utils.bytebuffer.MinecraftByteBufferDeserialize
import io.layercraft.packetlib.utils.bytebuffer.MinecraftByteBufferSerialize
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.nio.ByteBuffer
import java.util.*
import kotlin.test.assertEquals

internal class MinecraftByteTest {

    @Test
    fun testBoolean() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeBoolean(true)
        serialize.writeBoolean(false)

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assert(deserialize.readBoolean())
        assert(!deserialize.readBoolean())
    }

    @Test
    fun testByte() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeByte(0)
        serialize.writeByte(1)
        serialize.writeByte(127)
        serialize.writeByte(-128)
        serialize.writeByte(-1)

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readByte(), 0.toByte())
        assertEquals(deserialize.readByte(), 1.toByte())
        assertEquals(deserialize.readByte(), 127.toByte())
        assertEquals(deserialize.readByte(), (-128).toByte())
        assertEquals(deserialize.readByte(), (-1).toByte())
    }

    @Test
    fun testUByte() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeUByte(0.toUByte())
        serialize.writeUByte(1.toUByte())
        serialize.writeUByte(127.toUByte())
        serialize.writeUByte(128.toUByte())
        serialize.writeUByte(255.toUByte())

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readUByte(), 0.toUByte())
        assertEquals(deserialize.readUByte(), 1.toUByte())
        assertEquals(deserialize.readUByte(), 127.toUByte())
        assertEquals(deserialize.readUByte(), 128.toUByte())
        assertEquals(deserialize.readUByte(), 255.toUByte())
    }

    @Test
    fun testShort() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeShort(0)
        serialize.writeShort(1)
        serialize.writeShort(32767)
        serialize.writeShort(-32768)
        serialize.writeShort(-1)

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readShort(), 0.toShort())
        assertEquals(deserialize.readShort(), 1.toShort())
        assertEquals(deserialize.readShort(), 32767.toShort())
        assertEquals(deserialize.readShort(), (-32768).toShort())
        assertEquals(deserialize.readShort(), (-1).toShort())
    }

    @Test
    fun testUShort() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeUShort(0.toUShort())
        serialize.writeUShort(1.toUShort())
        serialize.writeUShort(32767.toUShort())
        serialize.writeUShort(32768.toUShort())
        serialize.writeUShort(65535.toUShort())

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readUShort(), 0.toUShort())
        assertEquals(deserialize.readUShort(), 1.toUShort())
        assertEquals(deserialize.readUShort(), 32767.toUShort())
        assertEquals(deserialize.readUShort(), 32768.toUShort())
        assertEquals(deserialize.readUShort(), 65535.toUShort())
    }

    @Test
    fun testInt() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeInt(0)
        serialize.writeInt(1)
        serialize.writeInt(2147483647)
        serialize.writeInt(-2147483648)
        serialize.writeInt(-1)

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readInt(), 0)
        assertEquals(deserialize.readInt(), 1)
        assertEquals(deserialize.readInt(), 2147483647)
        assertEquals(deserialize.readInt(), -2147483648)
        assertEquals(deserialize.readInt(), -1)
    }

    @Test
    fun testVarInt() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeVarInt(0)
        serialize.writeVarInt(1)
        serialize.writeVarInt(2147483647)
        serialize.writeVarInt(-2147483648)
        serialize.writeVarInt(-1)

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readVarInt(), 0)
        assertEquals(deserialize.readVarInt(), 1)
        assertEquals(deserialize.readVarInt(), 2147483647)
        assertEquals(deserialize.readVarInt(), -2147483648)
        assertEquals(deserialize.readVarInt(), -1)
    }

    @Test
    fun testLong() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeLong(0)
        serialize.writeLong(1)
        serialize.writeLong(92233854775807)
        serialize.writeLong(Long.MAX_VALUE)
        serialize.writeLong(-1)

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readLong(), 0)
        assertEquals(deserialize.readLong(), 1)
        assertEquals(deserialize.readLong(), 92233854775807)
        assertEquals(deserialize.readLong(), Long.MAX_VALUE)
        assertEquals(deserialize.readLong(), -1)
    }

    @Test
    fun testVarLong() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeVarLong(0)
        serialize.writeVarLong(1)
        serialize.writeVarLong(92233854775807)
        serialize.writeVarLong(Long.MAX_VALUE)
        serialize.writeVarLong(-1)

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readVarLong(), 0)
        assertEquals(deserialize.readVarLong(), 1)
        assertEquals(deserialize.readVarLong(), 92233854775807)
        assertEquals(deserialize.readVarLong(), Long.MAX_VALUE)
        assertEquals(deserialize.readVarLong(), -1)
    }

    @Test
    fun testFloat() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeFloat(0f)
        serialize.writeFloat(1f)
        serialize.writeFloat(1.5f)
        serialize.writeFloat(-1.5f)
        serialize.writeFloat(-1f)

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readFloat(), 0f)
        assertEquals(deserialize.readFloat(), 1f)
        assertEquals(deserialize.readFloat(), 1.5f)
        assertEquals(deserialize.readFloat(), -1.5f)
        assertEquals(deserialize.readFloat(), -1f)
    }

    @Test
    fun testDouble() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeDouble(0.0)
        serialize.writeDouble(1.0)
        serialize.writeDouble(1.5)
        serialize.writeDouble(-1.5)
        serialize.writeDouble(-1.0)

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readDouble(), 0.0)
        assertEquals(deserialize.readDouble(), 1.0)
        assertEquals(deserialize.readDouble(), 1.5)
        assertEquals(deserialize.readDouble(), -1.5)
        assertEquals(deserialize.readDouble(), -1.0)
    }

    @Test
    fun testString() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeString("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")
        serialize.writeString("Hello World!")
        serialize.writeString("Newspicel", 16)

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readString(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")
        assertEquals(deserialize.readString(), "Hello World!")
        assertEquals(deserialize.readString(16), "Newspicel")
    }

    @Test
    fun testChat() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeChat("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readChat(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")
    }

    @Test
    fun testIdentifier() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeIdentifier("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readIdentifier(), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\",./<>?`~")
    }

    @Test
    fun testVarIntByteArray() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeVarIntByteArray(byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertArrayEquals(deserialize.readVarIntByteArray(), byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
    }

    data class TestDataType(
        val int: Int,
        val float: Float,
        val string: String,
    )

    @Test
    fun testVarIntArray() {
        val data = listOf(TestDataType(1, 1f, "fghjkl"), TestDataType(2, 2f, "qwerty"), TestDataType(3, 3f, "asdfgh"))
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeVarIntArray(data) { arrayValue, arrayOutput ->
            arrayOutput.writeVarInt(arrayValue.int)
            arrayOutput.writeFloat(arrayValue.float)
            arrayOutput.writeString(arrayValue.string)
        }

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        val result = deserialize.readVarIntArray { arrayInput ->
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
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeRemainingArray(data) { arrayValue, arrayOutput ->
            arrayOutput.writeInt(arrayValue.int)
            arrayOutput.writeFloat(arrayValue.float)
            arrayOutput.writeString(arrayValue.string)
        }

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        val result = deserialize.readRemainingArray { arrayInput ->
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
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeRemainingByteArray(byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertArrayEquals(deserialize.readRemainingByteArray(), byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
    }

    @Test
    fun testPosition() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        val position = Position(1, 2, 3)
        serialize.writePosition(position)

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readPosition(), position)
    }

    @Test
    fun testUUID() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        val uuid = UUID.randomUUID()
        serialize.writeUUID(uuid)

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readUUID(), uuid)
    }

    @Test
    fun testAngle() {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeAngle(122.34375f)

        bytebuffer.flip()
        val deserialize = MinecraftByteBufferDeserialize(bytebuffer)
        assertEquals(deserialize.readAngle(), 122.34375f)
    }
}