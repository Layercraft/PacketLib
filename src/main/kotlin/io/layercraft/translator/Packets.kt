package io.layercraft.translator

import io.layercraft.translator.packets.ClientPacket
import io.layercraft.translator.packets.Packet
import io.layercraft.translator.packets.ServerPacket
import io.layercraft.translator.packets.client.Handshake
import kotlinx.serialization.KSerializer
import kotlin.reflect.KClass


typealias PacketId = Int

enum class Packets(
    val client: List<PacketType<out ClientPacket>>,
    val server: List<PacketType<out ServerPacket>>
) {
    HANDSHAKE(
        client = listOf(
            PacketType(0x00, Handshake::class, Handshake.serializer())
        ),
        server = listOf()
    ),
    STATUS(
        client = listOf(),
        server = listOf()
    ),
    LOGIN(
        client = listOf(),
        server = listOf()
    ),
    PLAY(
        client = listOf(),
        server = listOf()
    );

    companion object
}

data class PacketType<T : Packet>(
    val id: PacketId,
    val kclass: KClass<T>,
    val serializer: KSerializer<T>
)
