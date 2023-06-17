package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Simulation Distance | 0x58 | play | clientbound
 *
 * @param distance distance
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Simulation_Distance">https://wiki.vg/Protocol#Set_Simulation_Distance</a>
 */

data class SimulationDistancePacket(
    val distance: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<SimulationDistancePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SimulationDistancePacket {
            val distance = input.readVarInt()

            return SimulationDistancePacket(distance)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SimulationDistancePacket) {
            output.writeVarInt(value.distance)
        }
    }
}