package io.layercraft.packetlib.utils

import io.ktor.utils.io.core.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MinecraftStringTest {

    @Test
    fun testString() {
        val packetWrite = BytePacketBuilder()

        val str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#\$%^&*()-=_+[]{};':\",./<>?`~"

        MinecraftStringUtils.writeString(
            MINECRAFT_MAX_STRING_LENGTH,
            str,
            packetWrite.minecraft,
        )

        val packetRead = packetWrite.build()

        MinecraftStringUtils.readString(
            MINECRAFT_MAX_STRING_LENGTH,
            packetRead.minecraft,
        ).let {
            assertEquals(str, it)
        }
    }
}