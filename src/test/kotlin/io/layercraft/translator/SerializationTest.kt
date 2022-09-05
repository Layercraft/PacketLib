package io.layercraft.translator

import io.layercraft.translator.packets.client.Handshake
import io.layercraft.translator.packets.client.HandshakeNextState
import io.layercraft.translator.packets.client.login.LoginStart
import io.layercraft.translator.packets.server.login.EncryptionRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class SerializationTest {
    @Test
    fun `test normal serialization`(){
        val packet = Handshake(ProtocolVersion.V_1_19_2.protocolNumber, "localhost", (25565).toUShort(), HandshakeNextState.LOGIN)

        val bytes = TranslatorAPI.encodeToByteArray(Handshake.serializer(), packet)

        val decoded = TranslatorAPI.decodeFromByteArray(Handshake.serializer(), bytes)

        Assertions.assertEquals(packet, decoded)
    }

    @Test
    fun `test normal serialization from raw bytes`(){
        val rawPacket = byteArrayOf(-0x08, 0x05, 0x09, 0x6C, 0x6F, 0x63, 0x61, 0x6C, 0x68, 0x6F, 0x73, 0x74, 0x63, -0x23, 0x02) //export from wireshark

        val decoded: Handshake = TranslatorAPI.decodeFromByteArray(Handshake.serializer(), rawPacket)

        Assertions.assertEquals(ProtocolVersion.V_1_19_2.protocolNumber, decoded.version)
        Assertions.assertEquals("localhost", decoded.address)
        Assertions.assertEquals(25565, decoded.port.toInt())
        Assertions.assertEquals(HandshakeNextState.LOGIN, decoded.nextState)
    }

    @Test
    fun `test serialization with bytearray`(){

        val exampleByteArray = byteArrayOf(0x04, 0x6C, 0x6F, 0x63, 0x61, 0x6C, 0x68, 0x6F, 0x73, 0x74, 0x00, 0x7F, 0x00, 0x00)

        val packet =
            EncryptionRequest(
                "",
                exampleByteArray,
                exampleByteArray
            )

        val bytes = TranslatorAPI.encodeToByteArray(EncryptionRequest.serializer(), packet)

        val decoded = TranslatorAPI.decodeFromByteArray(EncryptionRequest.serializer(), bytes)

        Assertions.assertEquals(packet.serverId, decoded.serverId)
        Assertions.assertArrayEquals(packet.publicKey, decoded.publicKey)
        Assertions.assertArrayEquals(packet.verifyToken, decoded.verifyToken)
    }

    @Test
    fun `test serialization with custom serializer`() {
        val exampleByteArray = byteArrayOf(0x04, 0x6C, 0x6F, 0x63, 0x61, 0x6C, 0x68, 0x6F, 0x73, 0x74, 0x00, 0x7F, 0x00, 0x00)

        val packet: LoginStart =
            LoginStart(
                "Newspicel",
                true,
                0x7F000001,
                exampleByteArray,
                exampleByteArray,
                true,
                UUID.randomUUID())

        val serialized = LoginStart.serializer()

        val bytes = TranslatorAPI.encodeToByteArray(serialized, packet)

        val decoded = TranslatorAPI.decodeFromByteArray(serialized, bytes)

        assertEquals(packet.name, decoded.name)
        assertEquals(packet.hasSigData, decoded.hasSigData)
        assertEquals(packet.timestamp, decoded.timestamp)
        assertContentEquals(packet.signature, decoded.signature)
        assertContentEquals(packet.publicKey, decoded.publicKey)
        assertEquals(packet.hasPlayerUUID, decoded.hasPlayerUUID)
        assertEquals(packet.playerUUID, decoded.playerUUID)
    }
}