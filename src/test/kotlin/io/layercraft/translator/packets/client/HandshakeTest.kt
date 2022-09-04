package io.layercraft.translator.packets.client

import io.layercraft.translator.ProtocolVersion
import io.layercraft.translator.TranslatorAPI
import io.layercraft.translator.packets.server.login.EncryptionRequest
import io.layercraft.translator.utils.MinecraftVarIntUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HandshakeTest{

    @Test
    fun testHandshakePacket(){

        val packet =
            Handshake(ProtocolVersion.V_1_19_2.protocolNumber, "localhost", 25565, HandshakeNextState.LOGIN)

        val bytes = TranslatorAPI.encodeToByteArray(Handshake.serializer(), packet)

        val decoded = TranslatorAPI.decodeFromByteArray(Handshake.serializer(), bytes)

        assertEquals(packet, decoded)
    }

    @Test
    fun testByteArray(){

        val exampleByteArray = byteArrayOf(0x04, 0x6C, 0x6F, 0x63, 0x61, 0x6C, 0x68, 0x6F, 0x73, 0x74, 0x00, 0x7F, 0x00, 0x00)

        val packet =
            EncryptionRequest(
                "",
                exampleByteArray,
                exampleByteArray
            )

        val bytes = TranslatorAPI.encodeToByteArray(EncryptionRequest.serializer(), packet)

        val decoded = TranslatorAPI.decodeFromByteArray(EncryptionRequest.serializer(), bytes)

        assertEquals(packet.hashCode(), decoded.hashCode())
    }
}
