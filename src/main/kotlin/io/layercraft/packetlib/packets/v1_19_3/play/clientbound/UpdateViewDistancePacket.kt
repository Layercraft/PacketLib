package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Render Distance | 0x4b | play | clientbound
 *
 * @param viewDistance viewDistance
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Render_Distance">https://wiki.vg/Protocol#Set_Render_Distance</a>
 */

data class UpdateViewDistancePacket(
    val viewDistance: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<UpdateViewDistancePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): UpdateViewDistancePacket {
            val viewDistance = input.readVarInt()

            return UpdateViewDistancePacket(viewDistance)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: UpdateViewDistancePacket) {
            output.writeVarInt(value.viewDistance)
        }
    }
}