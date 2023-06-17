package io.layercraft.packetlib.utils

import io.layercraft.packetlib.MinecraftByteBufferSerialize
import io.layercraft.packetlib.types.MinecraftVarIntUtils
import io.layercraft.packetlib.types.MinecraftVarLongUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.ByteBuffer

internal class MinecraftNumberUtilsTest {

    private fun numberToBytes(number: Long): ByteArray {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeVarLong(number)

        val array = ByteArray(bytebuffer.position())
        bytebuffer.rewind()
        bytebuffer.get(array)

        return array
    }

    private fun numberToBytes(number: Int): ByteArray {
        val bytebuffer = ByteBuffer.allocate(1024)
        val serialize = MinecraftByteBufferSerialize(bytebuffer)
        serialize.writeVarInt(number)

        val array = ByteArray(bytebuffer.position())
        bytebuffer.rewind()
        bytebuffer.get(array)

        return array
    }

    @Test
    fun varLongBytesCount() {
        assertEquals(numberToBytes(0).size, MinecraftVarLongUtils.varLongBytesCount(0))
        assertEquals(numberToBytes(1).size, MinecraftVarLongUtils.varLongBytesCount(1))
        assertEquals(numberToBytes(127).size, MinecraftVarLongUtils.varLongBytesCount(127))
        assertEquals(numberToBytes(128).size, MinecraftVarLongUtils.varLongBytesCount(128))
        assertEquals(numberToBytes(255).size, MinecraftVarLongUtils.varLongBytesCount(255))
        assertEquals(numberToBytes(2147483647).size, MinecraftVarLongUtils.varLongBytesCount(2147483647))
        assertEquals(numberToBytes(2147483648).size, MinecraftVarLongUtils.varLongBytesCount(2147483648))
        assertEquals(numberToBytes(9223372036854775807).size, MinecraftVarLongUtils.varLongBytesCount(9223372036854775807))

        for (i in 0..100) {
            val random = (Math.random() * Long.MAX_VALUE).toLong()
            assertEquals(numberToBytes(random).size, MinecraftVarLongUtils.varLongBytesCount(random))
        }
    }

    @Test
    fun varIntBytesCount() {
        assertEquals(numberToBytes(0).size, MinecraftVarIntUtils.varIntBytesCount(0))
        assertEquals(numberToBytes(1).size, MinecraftVarIntUtils.varIntBytesCount(1))
        assertEquals(numberToBytes(127).size, MinecraftVarIntUtils.varIntBytesCount(127))
        assertEquals(numberToBytes(128).size, MinecraftVarIntUtils.varIntBytesCount(128))
        assertEquals(numberToBytes(255).size, MinecraftVarIntUtils.varIntBytesCount(255))
        assertEquals(numberToBytes(256).size, MinecraftVarIntUtils.varIntBytesCount(256))
        assertEquals(numberToBytes(32767).size, MinecraftVarIntUtils.varIntBytesCount(32767))
        assertEquals(numberToBytes(32768).size, MinecraftVarIntUtils.varIntBytesCount(32768))
        assertEquals(numberToBytes(2147483647).size, MinecraftVarIntUtils.varIntBytesCount(2147483647))

        for (i in 0..100) {
            val random = (Math.random() * Int.MAX_VALUE).toInt()
            assertEquals(numberToBytes(random).size, MinecraftVarIntUtils.varIntBytesCount(random))
        }
    }
}