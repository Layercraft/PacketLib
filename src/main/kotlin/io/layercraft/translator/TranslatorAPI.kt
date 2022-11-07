package io.layercraft.translator

import io.ktor.utils.io.core.*
import io.layercraft.translator.codec.MinecraftCodec
import io.layercraft.translator.packets.Packet
import io.layercraft.translator.packets.PacketDirection
import io.layercraft.translator.packets.PacketSerializer
import io.layercraft.translator.packets.PacketState
import io.layercraft.translator.utils.minecraft

object TranslatorAPI{

    fun <T : Packet> decodeFromByteArray(bytes: ByteArray, serializer: PacketSerializer<T>): T {
        val packetRead = ByteReadPacket(bytes)
        return serializer.serialize(packetRead.minecraft)
    }

    fun <T : Packet> encodeToByteArray(value: T, serializer: PacketSerializer<T>): ByteArray {
        val packetWrite = BytePacketBuilder()

        serializer.deserialize(packetWrite.minecraft, value)
        return packetWrite.build().readBytes()
    }

    fun decodeFromInputWithCodec(input: Input, codec: MinecraftCodec, packetDirection: PacketDirection, packetState: PacketState, packetId: Int): Packet? {
        return codec.getCodecPacket(packetDirection, packetState, packetId)?.packetSerializer?.serialize(input.minecraft)
    }

    fun encodeToOutputWithCodec(value: Packet, codec: MinecraftCodec, output: Output) {
        codec.getCodecPacketFromPacket(value)?.packetSerializer!!.deserialize(output.minecraft, value)
    }
}
