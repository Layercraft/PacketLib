package io.layercraft.packetlib.packets

import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

interface Packet
interface ServerBoundPacket : Packet
interface ClientBoundPacket : Packet

interface PacketSerializer<T> where T : Packet {
    fun deserialize(input: MCProtocolDeserializer<*>): T

    fun serialize(output: MCProtocolSerializer<*>, value: T)
}

interface PacketPart

interface PacketPartSerializer<T> where T : PacketPart {
    fun deserialize(input: MCProtocolDeserializer<*>): T

    fun serialize(output: MCProtocolSerializer<*>, value: T)
}