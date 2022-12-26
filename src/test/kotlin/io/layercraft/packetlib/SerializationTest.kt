package io.layercraft.packetlib

internal class SerializationTest {
    /*@Test
    fun `test normal serialization`() {
        val packet = HandshakePacket(ProtocolVersion.V_1_19_2, "localhost", (25565).toUShort(), HandshakeNextState.LOGIN)

        val bytes = TranslatorAPI.encodeToByteArray(packet, HandshakePacket)

        val decoded = TranslatorAPI.decodeFromByteArray(bytes, HandshakePacket)

        Assertions.assertEquals(packet, decoded)
    }

    @Test
    fun `test normal serialization from raw bytes`() {
        val rawPacket = byteArrayOf(-0x08, 0x05, 0x09, 0x6C, 0x6F, 0x63, 0x61, 0x6C, 0x68, 0x6F, 0x73, 0x74, 0x63, -0x23, 0x01) // export from wireshark

        val decoded: HandshakePacket = TranslatorAPI.decodeFromByteArray(rawPacket, HandshakePacket)

        Assertions.assertEquals(ProtocolVersion.V_1_19_2, decoded.protocolVersion)
        Assertions.assertEquals("localhost", decoded.address)
        Assertions.assertEquals(25565, decoded.port.toInt())
        Assertions.assertEquals(HandshakeNextState.STATUS, decoded.nextState)
    }

    @Test
    fun `test serialization with bytearray`() {
        val exampleByteArray = byteArrayOf(0x04, 0x6C, 0x6F, 0x63, 0x61, 0x6C, 0x68, 0x6F, 0x73, 0x74, 0x00, 0x7F, 0x00, 0x00)

        val packet =
            EncryptionRequestPacket(
                "",
                exampleByteArray,
                exampleByteArray,
            )

        val bytes = TranslatorAPI.encodeToByteArray(packet, EncryptionRequestPacket)

        val decoded = TranslatorAPI.decodeFromByteArray(bytes, EncryptionRequestPacket)

        Assertions.assertEquals(packet.serverId, decoded.serverId)
        Assertions.assertArrayEquals(packet.publicKey, decoded.publicKey)
        Assertions.assertArrayEquals(packet.verifyToken, decoded.verifyToken)
    }

    @Test
    fun `test serialization with custom serializer`() {
        val exampleByteArray = byteArrayOf(0x04, 0x6C, 0x6F, 0x63, 0x61, 0x6C, 0x68, 0x6F, 0x73, 0x74, 0x00, 0x7F, 0x00, 0x00)

        val packet = LoginStartPacket(
            "Newspicel",
            true,
            0x7F000001,
            exampleByteArray,
            exampleByteArray,
            true,
            UUID.randomUUID(),
        )

        val bytes = TranslatorAPI.encodeToByteArray(packet, LoginStartPacket)

        val decoded = TranslatorAPI.decodeFromByteArray(bytes, LoginStartPacket)

        assertEquals(packet.name, decoded.name)
        assertEquals(packet.hasSigData, decoded.hasSigData)
        assertEquals(packet.timestamp, decoded.timestamp)
        assertContentEquals(packet.signature, decoded.signature)
        assertContentEquals(packet.publicKey, decoded.publicKey)
        assertEquals(packet.hasPlayerUUID, decoded.hasPlayerUUID)
        assertEquals(packet.playerUUID, decoded.playerUUID)
    }*/
}