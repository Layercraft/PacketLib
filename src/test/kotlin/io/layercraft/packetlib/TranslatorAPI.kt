package io.layercraft.packetlib

import io.layercraft.packetlib.packets.Packet
import io.layercraft.packetlib.packets.PacketSerializer
import io.layercraft.packetlib.utils.bytebuffer.MinecraftByteBufferDeserialize
import io.layercraft.packetlib.utils.bytebuffer.MinecraftByteBufferSerialize
import java.nio.ByteBuffer

object TranslatorAPI {

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
}