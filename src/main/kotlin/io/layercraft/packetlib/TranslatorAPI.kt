package io.layercraft.packetlib

import io.ktor.utils.io.core.*
import io.layercraft.packetlib.codec.MinecraftCodec
import io.layercraft.packetlib.packets.Packet
import io.layercraft.packetlib.packets.PacketDirection
import io.layercraft.packetlib.packets.PacketSerializer
import io.layercraft.packetlib.packets.PacketState
import io.layercraft.packetlib.utils.minecraft

object TranslatorAPI {

    fun <T : Packet> decodeFromByteArray(bytes: ByteArray, serializer: PacketSerializer<T>): T {
        val packetRead = ByteReadPacket(bytes)
        return serializer.deserialize(packetRead.minecraft)
    }

    fun <T : Packet> encodeToByteArray(value: T, serializer: PacketSerializer<T>): ByteArray {
        val packetWrite = BytePacketBuilder()

        serializer.serialize(packetWrite.minecraft, value)
        return packetWrite.build().readBytes()
    }

    fun decodeFromInputWithCodec(input: Input, codec: MinecraftCodec, packetDirection: PacketDirection, packetState: PacketState, packetId: Int): Packet? {
        return codec.getCodecPacket(packetDirection, packetState, packetId)?.packetSerializer?.deserialize(input.minecraft)
    }

    fun encodeToOutputWithCodec(value: Packet, codec: MinecraftCodec, output: Output) {
        codec.getCodecPacketFromPacket(value)?.packetSerializer!!.serialize(output.minecraft, value)
    }
}