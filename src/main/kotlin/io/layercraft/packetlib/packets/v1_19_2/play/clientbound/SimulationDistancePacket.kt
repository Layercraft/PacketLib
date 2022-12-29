package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x5a | play | clientbound
 *
 * @property distance distance
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x5a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SimulationDistancePacket(
    val distance: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<SimulationDistancePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SimulationDistancePacket {
            val distance = input.readVarInt()

            return SimulationDistancePacket(distance)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SimulationDistancePacket) {
            output.writeVarInt(value.distance)
        }
    }
}