package io.layercraft.packetlib.packets

import io.ktor.utils.io.core.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

interface Packet {
    val bound: PacketDirection
}

interface ServerBoundPacket : Packet {
    override val bound: PacketDirection
        get() = PacketDirection.SERVERBOUND
}
interface ClientBoundPacket : Packet {
    override val bound: PacketDirection
        get() = PacketDirection.CLIENTBOUND
}

interface PacketSerializer<T> where T : Packet {
    fun serialize(input: MinecraftProtocolDeserializeInterface<*>): T

    fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: T)
}

@Target(AnnotationTarget.CLASS)
annotation class MinecraftPacket(val packetId: Int, val state: PacketState, val direction: PacketDirection)

enum class PacketDirection {
    SERVERBOUND,
    CLIENTBOUND,
}

enum class PacketState {
    HANDSHAKING,
    STATUS,
    LOGIN,
    PLAY,
}