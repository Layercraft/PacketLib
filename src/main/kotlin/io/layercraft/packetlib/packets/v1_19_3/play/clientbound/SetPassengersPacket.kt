package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Passengers | 0x55 | play | clientbound
 *
 * @param entityId entityId
 * @param passengers passengers
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Passengers">https://wiki.vg/Protocol#Set_Passengers</a>
 */

data class SetPassengersPacket(
    val entityId: Int, // varint
    val passengers: List<Int>, // varint array of varint
) : ClientBoundPacket {
    companion object : PacketSerializer<SetPassengersPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SetPassengersPacket {
            val entityId = input.readVarInt()
            val passengers = input.readVarIntArray { arrayInput -> arrayInput.readVarInt() }

            return SetPassengersPacket(entityId, passengers)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SetPassengersPacket) {
            output.writeVarInt(value.entityId)
            output.writeVarIntArray(value.passengers) { arrayValue, arrayOutput -> arrayOutput.writeVarInt(arrayValue) }
        }
    }
}