package io.layercraft.translator.utils

import io.ktor.utils.io.core.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MinecraftVarIntUtilsTest {

    @Test
    fun testVarInt() {
        testNumber(0)
        testNumber(1)
        testNumber(127)
        testNumber(128)
        testNumber(255)
        testNumber(256)
        testNumber(32767)
        testNumber(32768)
        testNumber(65535)
        testNumber(65536)
        testNumber(2147483647)

        for (i in 0..100) {
            val random = (Math.random() * Int.MAX_VALUE).toInt()
            testNumber(random)
        }
    }

    private fun testNumber(number: Int) {
        val packetWrite = BytePacketBuilder()


        MinecraftVarIntUtils.writeVarInt(number, packetWrite)

        val packetRead = packetWrite.build()

        assertEquals(number, MinecraftVarIntUtils.readVarInt(packetRead))
    }

    private fun numberToBytes(number: Int): ByteArray {
        val packetWrite = BytePacketBuilder()


        MinecraftVarIntUtils.writeVarInt(number, packetWrite)

        return packetWrite.build().readBytes()
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
