package io.layercraft.packetlib

import io.layercraft.packetlib.types.NbtCodec
import io.layercraft.packetlib.types.Position
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.nio.ByteBuffer

internal class NbtCodecTest {

    /**
     * { "": { "byte": 1b, "str": "ab", "list": [1, 2], "nested": { "long": 2L } } }
     */
    private val namedCompound: ByteArray = byteArrayOf(
        10, 0, 0, // TAG_Compound, name ""
        1, 0, 4, 'b'.code.toByte(), 'y'.code.toByte(), 't'.code.toByte(), 'e'.code.toByte(), 1, // byte
        8, 0, 3, 's'.code.toByte(), 't'.code.toByte(), 'r'.code.toByte(), 0, 2, 'a'.code.toByte(), 'b'.code.toByte(), // string
        9, 0, 4, 'l'.code.toByte(), 'i'.code.toByte(), 's'.code.toByte(), 't'.code.toByte(), 3, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 2, // list of int
        10, 0, 6, 'n'.code.toByte(), 'e'.code.toByte(), 's'.code.toByte(), 't'.code.toByte(), 'e'.code.toByte(), 'd'.code.toByte(),
        4, 0, 4, 'l'.code.toByte(), 'o'.code.toByte(), 'n'.code.toByte(), 'g'.code.toByte(), 0, 0, 0, 0, 0, 0, 0, 2, // long
        0, // end nested
        0, // end root
    )

    private fun deserializer(bytes: ByteArray) = MinecraftByteBufferDeserialize(ByteBuffer.wrap(bytes))

    @Test
    fun `named compound round trip`() {
        val input = deserializer(namedCompound + byteArrayOf(42)) // trailing byte must remain
        val nbt = input.readNbt()
        assertArrayEquals(namedCompound, nbt)
        assertEquals(42, input.readByte())
    }

    @Test
    fun `anonymous compound round trip`() {
        val anonymous = byteArrayOf(10) + namedCompound.drop(3).toByteArray()
        val input = deserializer(anonymous + byteArrayOf(7))
        val nbt = input.readAnonymousNbt()
        assertArrayEquals(anonymous, nbt)
        assertEquals(7, input.readByte())
    }

    @Test
    fun `optional nbt absent`() {
        val input = deserializer(byteArrayOf(0, 13))
        assertNull(input.readOptionalNbt())
        assertEquals(13, input.readByte())
    }

    @Test
    fun `nbt codec object is reachable`() {
        val input = deserializer(namedCompound)
        assertArrayEquals(namedCompound, NbtCodec.readNamed(input))
    }

    @Test
    fun `legacy position round trip`() {
        val position = Position(-1000, -64, 123456)
        assertEquals(position, Position.longToLegacyPosition(Position.legacyPositionToLong(position)))

        val modern = Position(-30_000_000 + 1, -2048, 29_999_999)
        assertEquals(modern, Position.longToPosition(Position.positionToLong(modern)))
    }
}
