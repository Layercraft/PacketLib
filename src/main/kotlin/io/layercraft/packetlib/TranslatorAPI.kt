package io.layercraft.packetlib

import io.layercraft.packetlib.codec.MinecraftCodec
import io.layercraft.packetlib.packets.Packet
import io.layercraft.packetlib.packets.PacketDirection
import io.layercraft.packetlib.packets.PacketSerializer
import io.layercraft.packetlib.packets.PacketState
import io.layercraft.packetlib.utils.MinecraftByteBufferDeserialize
import io.layercraft.packetlib.utils.MinecraftByteBufferSerialize
import java.nio.ByteBuffer

object TranslatorAPI {

    fun <T : Packet> decodeFromByteArray(bytes: ByteArray, serializer: PacketSerializer<T>): T {
        val bytebuffer = ByteBuffer.wrap(bytes)
        return serializer.deserialize(MinecraftByteBufferDeserialize(bytebuffer))
    }

    fun <T : Packet> encodeToByteArray(value: T, serializer: PacketSerializer<T>): ByteArray {
        val byteBuffer = ByteBuffer.allocate(16384)

        serializer.serialize(MinecraftByteBufferSerialize(byteBuffer), value)

        val array = ByteArray(byteBuffer.position())
        byteBuffer.rewind()
        byteBuffer.get(array)

        return array
    }

    fun decodeFromInputWithCodec(input: ByteBuffer, codec: MinecraftCodec, packetDirection: PacketDirection, packetState: PacketState, packetId: Int): Packet? {
        return codec.getCodecPacket(packetDirection, packetState, packetId)?.packetSerializer?.deserialize(MinecraftByteBufferDeserialize(input))
    }

    fun encodeToOutputWithCodec(value: Packet, codec: MinecraftCodec, output: ByteBuffer) {
        codec.getCodecPacketFromPacket(value)?.packetSerializer!!.serialize(MinecraftByteBufferSerialize(output), value)
    }
}