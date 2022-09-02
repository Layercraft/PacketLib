package io.layercraft.translator.utils

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MinecraftStringTest {

    @Test
    fun testString() {
        assertEquals("Hello, World!", MinecraftString.toString(MINECRAFT_MAX_STRING_LENGTH, MinecraftString.fromString("Hello, World!")))
        assertEquals("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-=_+[]{};':\",./<>?`~", MinecraftString.toString(MINECRAFT_MAX_STRING_LENGTH, MinecraftString.fromString("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-=_+[]{};':\",./<>?`~")))
        assertEquals("Hello, World!", MinecraftString.toString(255, MinecraftString.fromString("Hello, World!")))
    }
}
