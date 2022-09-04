package io.layercraft.translator.utils

import io.ktor.utils.io.core.*
import io.layercraft.translator.MinecraftString
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MinecraftStringTest {

    @Test
    fun testString() {
        val packetWrite = BytePacketBuilder()

        val str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#\$%^&*()-=_+[]{};':\",./<>?`~"

        MinecraftStringUtils.writeString(MINECRAFT_MAX_STRING_LENGTH,
            str,
            packetWrite::writeByte,
            packetWrite::writeFully
        )

        val packetRead = packetWrite.build()

        MinecraftStringUtils.readString(MINECRAFT_MAX_STRING_LENGTH,
            packetRead::readByte,
            packetRead::readBytes
        ).let {
            assertEquals(str, it)
        }
    }
}
