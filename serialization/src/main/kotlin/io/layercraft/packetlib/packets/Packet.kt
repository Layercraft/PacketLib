package io.layercraft.packetlib.packets

import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

const val MAX_PACKET_SIZE: Int = 2097151 // 3 bytes varint

interface Packet
interface ServerBoundPacket : Packet
interface ClientBoundPacket : Packet

interface PacketSerializer<T> where T : Packet {
    fun deserialize(input: MCProtocolDeserializer<*>): T

    fun serialize(output: MCProtocolSerializer<*>, value: T)
}