package io.layercraft.translator.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MinecraftVarLongTest {

    @Test
    fun testVarLong() {
        assertEquals(0, MinecraftVarLong.fromVarLong(MinecraftVarLong.toVarLong(0)))
        assertEquals(1, MinecraftVarLong.fromVarLong(MinecraftVarLong.toVarLong(1)))
        assertEquals(127, MinecraftVarLong.fromVarLong(MinecraftVarLong.toVarLong(127)))
        assertEquals(128, MinecraftVarLong.fromVarLong(MinecraftVarLong.toVarLong(128)))
        assertEquals(255, MinecraftVarLong.fromVarLong(MinecraftVarLong.toVarLong(255)))
        assertEquals(2147483647, MinecraftVarLong.fromVarLong(MinecraftVarLong.toVarLong(2147483647)))
        assertEquals(2147483648, MinecraftVarLong.fromVarLong(MinecraftVarLong.toVarLong(2147483648)))
        assertEquals(9223372036854775807, MinecraftVarLong.fromVarLong(MinecraftVarLong.toVarLong(9223372036854775807)))

        for (i in 0..100) {
            val random = (Math.random() * Long.MAX_VALUE).toLong()
            assertEquals(random, MinecraftVarLong.fromVarLong(MinecraftVarLong.toVarLong(random)))
        }
    }

    @Test
    fun varLongBytesCount() {
        assertEquals(1, MinecraftVarLong.varLongBytesCount(0))
        assertEquals(1, MinecraftVarLong.varLongBytesCount(1))
        assertEquals(1, MinecraftVarLong.varLongBytesCount(127))
        assertEquals(2, MinecraftVarLong.varLongBytesCount(128))
        assertEquals(2, MinecraftVarLong.varLongBytesCount(255))
        assertEquals(4, MinecraftVarLong.varLongBytesCount(2147483647))
        assertEquals(5, MinecraftVarLong.varLongBytesCount(2147483648))
        assertEquals(10, MinecraftVarLong.varLongBytesCount(9223372036854775807))
    }
}
