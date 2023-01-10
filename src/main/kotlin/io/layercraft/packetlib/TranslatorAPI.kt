package io.layercraft.packetlib

import io.layercraft.packetlib.codec.MinecraftCodec
import io.layercraft.packetlib.packets.Packet
import io.layercraft.packetlib.packets.PacketDirection
import io.layercraft.packetlib.packets.PacketSerializer
import io.layercraft.packetlib.packets.PacketState
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.utils.bytebuffer.MinecraftByteBufferDeserialize
import io.layercraft.packetlib.utils.bytebuffer.MinecraftByteBufferSerialize
import java.nio.ByteBuffer

object TranslatorAPI {

    private const val MAX_PACKET_SIZE: Int = 2097151 // 3 bytes varint

    fun <T : Packet> decodeFromByteArray(bytes: ByteArray, serializer: PacketSerializer<T>): T {
        val byteBuffer = ByteBuffer.wrap(bytes)
        val deserialize = MinecraftByteBufferDeserialize(byteBuffer)

        return serializer.deserialize(deserialize)
    }

    fun <T : Packet> encodeToByteArray(value: T, serializer: PacketSerializer<T>): ByteArray {
        val byteBuffer = ByteBuffer.allocate(MAX_PACKET_SIZE)
        val serialize = MinecraftByteBufferSerialize(byteBuffer)

        serializer.serialize(serialize, value)

        val size = byteBuffer.position()
        val byteArray = ByteArray(size)
        byteBuffer.get(0, byteArray, 0, size)

        return byteArray
    }

    fun decodeFromInputWithCodec(codec: MinecraftCodec, input: MinecraftProtocolDeserializeInterface<*>, packetDirection: PacketDirection, packetState: PacketState, packetId: Int): Packet? {
        return codec.getCodecPacket(packetDirection, packetState, packetId)?.packetSerializer?.deserialize(input)
    }

    fun encodeToOutputWithCodec(codec: MinecraftCodec, output: MinecraftProtocolSerializeInterface<*>, value: Packet) {
        codec.getCodecPacketFromPacket(value)?.packetSerializer?.serialize(output, value)
    }
}