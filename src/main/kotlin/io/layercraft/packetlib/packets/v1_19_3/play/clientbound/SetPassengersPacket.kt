package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Passengers | 0x55 | play | clientbound
 *
 * @param entityId entityId
 * @param passengers passengers
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Set_Passengers">https://wiki.vg/Protocol#Set_Passengers</a>
 */

@MinecraftPacket(id = 0x55, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SetPassengersPacket(
    val entityId: Int, // varint
    val passengers: List<Int>, // varint array of varint
) : ClientBoundPacket {
    companion object : PacketSerializer<SetPassengersPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SetPassengersPacket {
            val entityId = input.readVarInt()
            val passengers = input.readVarIntArray { arrayInput -> arrayInput.readVarInt() }

            return SetPassengersPacket(entityId, passengers)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SetPassengersPacket) {
            output.writeVarInt(value.entityId)
            output.writeVarIntArray(value.passengers) { arrayValue, arrayOutput -> arrayOutput.writeVarInt(arrayValue) }
        }
    }
}