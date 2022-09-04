package io.layercraft.translator.packets.client

import io.layercraft.translator.ProtocolVersion
import io.layercraft.translator.TranslatorAPI
import kotlinx.serialization.InternalSerializationApi
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HandshakeTest{

    @InternalSerializationApi
    @Test
    fun testHandshakePacket(){

        val packet =
            Handshake(ProtocolVersion.V_1_19_2.protocolNumber, "localhost", 25565, HandshakeNextState.LOGIN)

        val bytes = TranslatorAPI.encodeToByteArray(Handshake.serializer(), packet)

        val decoded = TranslatorAPI.decodeFromByteArray(Handshake.serializer(), bytes)

        assertEquals(packet, decoded)
    }
}
