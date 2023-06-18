package io.layercraft.packetlib.io.layercraft.packetlib

internal class SerializationTest {
    /*@Test
    fun `test normal serialization`() {
        val packet = SetProtocolPacket(ProtocolVersion.V1_19_3.protocolNumber, "localhost", (25565).toUShort(), 1)

        val bytes = TranslatorAPI.encodeToByteArray(packet, SetProtocolPacket)

        val decoded = TranslatorAPI.decodeFromByteArray(bytes, SetProtocolPacket)

        Assertions.assertEquals(packet, decoded)
    }

    @Test
    fun `test many serialization`() {
        val packet = SetProtocolPacket(ProtocolVersion.V1_19_3.protocolNumber, "localhost", (25565).toUShort(), 1)

        val bytes = TranslatorAPI.encodeToByteArray(packet, SetProtocolPacket)
        val decoded = TranslatorAPI.decodeFromByteArray(bytes, SetProtocolPacket)
        val bytes2 = TranslatorAPI.encodeToByteArray(decoded, SetProtocolPacket)

        Assertions.assertArrayEquals(bytes, bytes2)

        val decoded2 = TranslatorAPI.decodeFromByteArray(bytes2, SetProtocolPacket)
        val bytes3 = TranslatorAPI.encodeToByteArray(decoded2, SetProtocolPacket)

        Assertions.assertArrayEquals(bytes2, bytes3)

        val decoded3 = TranslatorAPI.decodeFromByteArray(bytes3, SetProtocolPacket)
        val bytes4 = TranslatorAPI.encodeToByteArray(decoded3, SetProtocolPacket)

        Assertions.assertArrayEquals(bytes3, bytes4)

        val decoded4 = TranslatorAPI.decodeFromByteArray(bytes4, SetProtocolPacket)
        val bytes5 = TranslatorAPI.encodeToByteArray(decoded4, SetProtocolPacket)

        Assertions.assertArrayEquals(bytes4, bytes5)

        val decoded5 = TranslatorAPI.decodeFromByteArray(bytes5, SetProtocolPacket)

        Assertions.assertEquals(packet, decoded5)
    }

    @Test
    fun `test normal serialization from raw bytes`() {
        val rawPacket = byteArrayOf(-0x08, 0x05, 0x09, 0x6C, 0x6F, 0x63, 0x61, 0x6C, 0x68, 0x6F, 0x73, 0x74, 0x63, -0x23, 0x01) // export from wireshark

        val decoded: SetProtocolPacket = TranslatorAPI.decodeFromByteArray(rawPacket, SetProtocolPacket)

        Assertions.assertEquals(ProtocolVersion.V1_19_2.protocolNumber, decoded.protocolVersion)
        Assertions.assertEquals("localhost", decoded.serverHost)
        Assertions.assertEquals(25565, decoded.serverPort.toInt())
        Assertions.assertEquals(1, decoded.nextState)
    }

    @Test
    fun `test serialization with bytearray`() {
        val exampleByteArray = byteArrayOf(0x04, 0x6C, 0x6F, 0x63, 0x61, 0x6C, 0x68, 0x6F, 0x73, 0x74, 0x00, 0x7F, 0x00, 0x00)

        val packet =
            EncryptionBeginPacket(
                "TestId",
                exampleByteArray,
                exampleByteArray,
            )

        val bytes = TranslatorAPI.encodeToByteArray(packet, EncryptionBeginPacket)

        val decoded = TranslatorAPI.decodeFromByteArray(bytes, EncryptionBeginPacket)

        Assertions.assertArrayEquals(packet.publicKey, decoded.publicKey)
        Assertions.assertArrayEquals(packet.verifyToken, decoded.verifyToken)
    }

    @Test
    fun `test serialization with custom serializer`() {
        val packet = LoginStartPacket(
            "Newspicel",
            true,
            UUID.randomUUID(),
        )

        val bytes = TranslatorAPI.encodeToByteArray(packet, LoginStartPacket)

        val decoded = TranslatorAPI.decodeFromByteArray(bytes, LoginStartPacket)

        Assertions.assertEquals(packet.username, decoded.username)
        Assertions.assertEquals(packet.hasPlayerUUID, decoded.hasPlayerUUID)
        Assertions.assertEquals(packet.playerUUID, decoded.playerUUID)
    }*/
}