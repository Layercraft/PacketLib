package io.layercraft.translator.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MinecraftVarIntTest {

    @Test
    fun testVarInt() {
        assertEquals(0, MinecraftVarInt.fromVarInt(MinecraftVarInt.toVarInt(0)))
        assertEquals(1, MinecraftVarInt.fromVarInt(MinecraftVarInt.toVarInt(1)))
        assertEquals(127, MinecraftVarInt.fromVarInt(MinecraftVarInt.toVarInt(127)))
        assertEquals(128, MinecraftVarInt.fromVarInt(MinecraftVarInt.toVarInt(128)))
        assertEquals(255, MinecraftVarInt.fromVarInt(MinecraftVarInt.toVarInt(255)))
        assertEquals(256, MinecraftVarInt.fromVarInt(MinecraftVarInt.toVarInt(256)))
        assertEquals(32767, MinecraftVarInt.fromVarInt(MinecraftVarInt.toVarInt(32767)))
        assertEquals(32768, MinecraftVarInt.fromVarInt(MinecraftVarInt.toVarInt(32768)))
        assertEquals(2147483647, MinecraftVarInt.fromVarInt(MinecraftVarInt.toVarInt(2147483647)))

        for (i in 0..100) {
            val random = (Math.random() * Int.MAX_VALUE).toInt()
            assertEquals(random, MinecraftVarInt.fromVarInt(MinecraftVarInt.toVarInt(random)))
        }
    }

    @Test
    fun varIntBytesCount() {
        assertEquals(MinecraftVarInt.toVarInt(0).size, MinecraftVarInt.varIntBytesCount(0))
        assertEquals(MinecraftVarInt.toVarInt(1).size, MinecraftVarInt.varIntBytesCount(1))
        assertEquals(MinecraftVarInt.toVarInt(127).size, MinecraftVarInt.varIntBytesCount(127))
        assertEquals(MinecraftVarInt.toVarInt(128).size, MinecraftVarInt.varIntBytesCount(128))
        assertEquals(MinecraftVarInt.toVarInt(255).size, MinecraftVarInt.varIntBytesCount(255))
        assertEquals(MinecraftVarInt.toVarInt(256).size, MinecraftVarInt.varIntBytesCount(256))
        assertEquals(MinecraftVarInt.toVarInt(32767).size, MinecraftVarInt.varIntBytesCount(32767))
        assertEquals(MinecraftVarInt.toVarInt(32768).size, MinecraftVarInt.varIntBytesCount(32768))
        assertEquals(MinecraftVarInt.toVarInt(2147483647).size, MinecraftVarInt.varIntBytesCount(2147483647))

        for (i in 0..100) {
            val random = (Math.random() * Int.MAX_VALUE).toInt()
            assertEquals(MinecraftVarInt.toVarInt(random).size, MinecraftVarInt.varIntBytesCount(random))
        }
    }
}
