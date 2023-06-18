package io.layercraft.packetlib.packets

import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

interface PacketPart

interface PacketPartSerializer<T> where T : PacketPart {
    fun deserialize(input: MCProtocolDeserializer<*>): T

    fun serialize(output: MCProtocolSerializer<*>, value: T)
}