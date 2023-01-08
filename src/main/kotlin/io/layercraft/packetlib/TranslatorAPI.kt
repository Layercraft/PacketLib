package io.layercraft.packetlib

import io.layercraft.packetlib.codec.MinecraftCodec
import io.layercraft.packetlib.packets.Packet
import io.layercraft.packetlib.packets.PacketDirection
import io.layercraft.packetlib.packets.PacketSerializer
import io.layercraft.packetlib.packets.PacketState
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.utils.stream.MinecraftInputStreamDeserialize
import io.layercraft.packetlib.utils.stream.MinecraftOutputStreamSerialize
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

object TranslatorAPI {

    fun <T : Packet> decodeFromByteArray(bytes: ByteArray, serializer: PacketSerializer<T>): T {
        val deserialize = MinecraftInputStreamDeserialize(ByteArrayInputStream(bytes))
        return serializer.deserialize(deserialize)
    }

    fun <T : Packet> encodeToByteArray(value: T, serializer: PacketSerializer<T>): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val serialize = MinecraftOutputStreamSerialize(byteStream)

        serializer.serialize(serialize, value)

        return byteStream.toByteArray()
    }

    fun decodeFromInputWithCodec(codec: MinecraftCodec, input: MinecraftProtocolDeserializeInterface<*>, packetDirection: PacketDirection, packetState: PacketState, packetId: Int): Packet? {
        return codec.getCodecPacket(packetDirection, packetState, packetId)?.packetSerializer?.deserialize(input)
    }

    fun encodeToOutputWithCodec(codec: MinecraftCodec, output: MinecraftProtocolSerializeInterface<*>, value: Packet) {
        codec.getCodecPacketFromPacket(value)?.packetSerializer?.serialize(output, value)
    }
}