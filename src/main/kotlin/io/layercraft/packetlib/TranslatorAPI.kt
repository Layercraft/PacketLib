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
import java.io.DataInputStream
import java.io.DataOutputStream

object TranslatorAPI {

    fun <T : Packet> decodeFromByteArray(bytes: ByteArray, serializer: PacketSerializer<T>): T {
        val byteStream = ByteArrayInputStream(bytes)
        val dataStream = DataInputStream(byteStream)
        val deserialize = MinecraftInputStreamDeserialize(dataStream)

        val result = serializer.deserialize(deserialize)

        byteStream.close()
        dataStream.close()
        return result
    }

    fun <T : Packet> encodeToByteArray(value: T, serializer: PacketSerializer<T>): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val dataStream = DataOutputStream(byteStream)
        val serialize = MinecraftOutputStreamSerialize(dataStream)

        serializer.serialize(serialize, value)

        val result = byteStream.toByteArray()

        byteStream.close()
        dataStream.close()
        return result
    }

    fun decodeFromInputWithCodec(codec: MinecraftCodec, input: MinecraftProtocolDeserializeInterface<*>, packetDirection: PacketDirection, packetState: PacketState, packetId: Int): Packet? {
        return codec.getCodecPacket(packetDirection, packetState, packetId)?.packetSerializer?.deserialize(input)
    }

    fun encodeToOutputWithCodec(codec: MinecraftCodec, output: MinecraftProtocolSerializeInterface<*>, value: Packet) {
        codec.getCodecPacketFromPacket(value)?.packetSerializer?.serialize(output, value)
    }
}