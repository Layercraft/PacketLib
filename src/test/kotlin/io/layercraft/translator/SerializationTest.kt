package io.layercraft.translator

import io.layercraft.translator.data.ProtocolVersion
import io.layercraft.translator.packets.handshake.data.HandshakeNextState
import io.layercraft.translator.packets.handshake.serverbound.Handshake
import io.layercraft.translator.packets.login.clientbound.EncryptionRequest
import io.layercraft.translator.packets.login.serverbound.LoginStart
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.UUID
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class SerializationTest {
    @Test
    fun `test normal serialization`() {
        val packet = Handshake(ProtocolVersion.V_1_19_2, "localhost", (25565).toUShort(), HandshakeNextState.LOGIN)

        val bytes = TranslatorAPI.encodeToByteArray(packet, Handshake)

        val decoded = TranslatorAPI.decodeFromByteArray(bytes, Handshake)

        Assertions.assertEquals(packet, decoded)
    }

    @Test
    fun `test normal serialization from raw bytes`() {
        val rawPacket = byteArrayOf(-0x08, 0x05, 0x09, 0x6C, 0x6F, 0x63, 0x61, 0x6C, 0x68, 0x6F, 0x73, 0x74, 0x63, -0x23, 0x01) // export from wireshark

        val decoded: Handshake = TranslatorAPI.decodeFromByteArray(rawPacket, Handshake)

        Assertions.assertEquals(ProtocolVersion.V_1_19_2, decoded.protocolVersion)
        Assertions.assertEquals("localhost", decoded.address)
        Assertions.assertEquals(25565, decoded.port.toInt())
        Assertions.assertEquals(HandshakeNextState.STATUS, decoded.nextState)
    }

    @Test
    fun `test serialization with bytearray`() {
        val exampleByteArray = byteArrayOf(0x04, 0x6C, 0x6F, 0x63, 0x61, 0x6C, 0x68, 0x6F, 0x73, 0x74, 0x00, 0x7F, 0x00, 0x00)

        val packet =
            EncryptionRequest(
                "",
                exampleByteArray,
                exampleByteArray,
            )

        val bytes = TranslatorAPI.encodeToByteArray(packet, EncryptionRequest)

        val decoded = TranslatorAPI.decodeFromByteArray(bytes, EncryptionRequest)

        Assertions.assertEquals(packet.serverId, decoded.serverId)
        Assertions.assertArrayEquals(packet.publicKey, decoded.publicKey)
        Assertions.assertArrayEquals(packet.verifyToken, decoded.verifyToken)
    }

    @Test
    fun `test serialization with custom serializer`() {
        val exampleByteArray = byteArrayOf(0x04, 0x6C, 0x6F, 0x63, 0x61, 0x6C, 0x68, 0x6F, 0x73, 0x74, 0x00, 0x7F, 0x00, 0x00)

        val packet = LoginStart(
            "Newspicel",
            true,
            0x7F000001,
            exampleByteArray,
            exampleByteArray,
            true,
            UUID.randomUUID(),
        )

        val bytes = TranslatorAPI.encodeToByteArray(packet, LoginStart)

        val decoded = TranslatorAPI.decodeFromByteArray(bytes, LoginStart)

        assertEquals(packet.name, decoded.name)
        assertEquals(packet.hasSigData, decoded.hasSigData)
        assertEquals(packet.timestamp, decoded.timestamp)
        assertContentEquals(packet.signature, decoded.signature)
        assertContentEquals(packet.publicKey, decoded.publicKey)
        assertEquals(packet.hasPlayerUUID, decoded.hasPlayerUUID)
        assertEquals(packet.playerUUID, decoded.playerUUID)
    }
}