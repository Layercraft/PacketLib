package io.layercraft.translator.packets

import io.ktor.utils.io.core.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface


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


interface PacketSerializer<T> where T:Packet {
    fun serialize(input: MinecraftProtocolDeserializeInterface<*>): T

    fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: T)
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
