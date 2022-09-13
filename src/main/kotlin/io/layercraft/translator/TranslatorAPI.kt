package io.layercraft.translator

import io.ktor.utils.io.core.*
import io.layercraft.translator.codec.MinecraftCodec
import io.layercraft.translator.packets.Packet
import io.layercraft.translator.packets.PacketDirection
import io.layercraft.translator.packets.PacketSerializer
import io.layercraft.translator.packets.PacketState

object TranslatorAPI{

    fun <T : Packet>decodeFromByteArray(bytes: ByteArray, serializer: PacketSerializer<T>): T {
        val packetRead = ByteReadPacket(bytes)
        return decodeFromInput(packetRead, serializer)
    }

    fun <T : Packet> encodeToByteArray(value: T, serializer: PacketSerializer<T>): ByteArray {
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

    fun decodeFromByteArray(input: ByteArray, codec: MinecraftCodec, packetDirection: PacketDirection, packetState: PacketState, packetId: Int): Packet? {
        return codec.getCodecPacket(packetDirection, packetState, packetId)?.let { decodeFromByteArray(input, it.packetSerializer) }
    }

    fun encodeToByteArray(value: Packet, codec: MinecraftCodec): ByteArray {
       return encodeToByteArray(value, codec.getCodecPacketFromPacket(value)?.packetSerializer!!)
    }



}
