package io.layercraft.translator

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.Packet
import io.layercraft.translator.packets.PacketSerializer

object TranslatorAPI{

    inline fun <reified T : Packet>decodeFromByteArray(bytes: ByteArray, serializer: PacketSerializer<T>): T {
        val packetRead = ByteReadPacket(bytes)
        return decodeFromInput(packetRead, serializer)
    }

    inline fun <reified T : Packet> encodeToByteArray(value: T, serializer: PacketSerializer<T>): ByteArray {
        val packetWrite = BytePacketBuilder()

        encodeToOutput(value, packetWrite, serializer)
        return packetWrite.build().readBytes()
    }

    fun <T: Packet> decodeFromInput(input: Input, serializer: PacketSerializer<T>): T {

        return serializer.serialize(input)
    }

    fun <T: Packet> encodeToOutput(value: T, output: Output, serializer: PacketSerializer<T>) {
        serializer.deserialize(output, value)
    }
}
