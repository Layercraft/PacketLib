package io.layercraft.translator.packets

import io.ktor.utils.io.core.*


interface Packet{
    val direction: PacketDirection
}

interface ServerBoundPacket: Packet {
    override val direction: PacketDirection
        get() = PacketDirection.SERVERBOUND
}
interface ClientBoundPacket: Packet {
    override val direction: PacketDirection
        get() = PacketDirection.CLIENTBOUND
}


interface PacketSerializer<T> where T: Packet {
    fun serialize(input: Input): T

    fun deserialize(output: Output, value: T)
}

@Target(AnnotationTarget.CLASS)
annotation class MinecraftPacket(val packetId: Int, val state: PacketState, val direction: PacketDirection)

enum class PacketDirection {
    SERVERBOUND,
    CLIENTBOUND
}

enum class PacketState {
    HANDSHAKE,
    STATUS,
    LOGIN,
    PLAY
}
