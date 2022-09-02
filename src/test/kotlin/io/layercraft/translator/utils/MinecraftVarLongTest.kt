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
        assertEquals(MinecraftVarLong.toVarLong(0).size, MinecraftVarLong.varLongBytesCount(0))
        assertEquals(MinecraftVarLong.toVarLong(1).size, MinecraftVarLong.varLongBytesCount(1))
        assertEquals(MinecraftVarLong.toVarLong(127).size, MinecraftVarLong.varLongBytesCount(127))
        assertEquals(MinecraftVarLong.toVarLong(128).size, MinecraftVarLong.varLongBytesCount(128))
        assertEquals(MinecraftVarLong.toVarLong(255).size, MinecraftVarLong.varLongBytesCount(255))
        assertEquals(MinecraftVarLong.toVarLong(2147483647).size, MinecraftVarLong.varLongBytesCount(2147483647))
        assertEquals(MinecraftVarLong.toVarLong(2147483648).size, MinecraftVarLong.varLongBytesCount(2147483648))
        assertEquals(MinecraftVarLong.toVarLong(9223372036854775807).size, MinecraftVarLong.varLongBytesCount(9223372036854775807))

        for (i in 0..100) {
            val random = (Math.random() * Long.MAX_VALUE).toLong()
            assertEquals(MinecraftVarLong.toVarLong(random).size, MinecraftVarLong.varLongBytesCount(random))
        }
    }
}
