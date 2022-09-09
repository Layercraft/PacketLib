package io.layercraft.translator.utils

import io.ktor.utils.io.core.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MinecraftVarLongUtilsTest {

    @Test
    fun testVarLong() {
        testNumber(0)
        testNumber(1)
        testNumber(127)
        testNumber(128)
        testNumber(255)
        testNumber(256)
        testNumber(2147483647)
        testNumber(2147483648)
        testNumber(9223372036854775807)

        for (i in 0..100) {
            val random = (Math.random() * Long.MAX_VALUE).toLong()
            testNumber(random)
        }
    }

    private fun testNumber(number: Long) {
        val packetWrite = BytePacketBuilder()


        MinecraftVarLongUtils.writeVarLong(number, packetWrite::writeByte)

        val packetRead = packetWrite.build()

        assertEquals(number, MinecraftVarLongUtils.readVarLong(packetRead::readByte))
    }

    private fun numberToBytes(number: Long): ByteArray {
        val packetWrite = BytePacketBuilder()


        MinecraftVarLongUtils.writeVarLong(number, packetWrite::writeByte)

        return packetWrite.build().readBytes()
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
}
