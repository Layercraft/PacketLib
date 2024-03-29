package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Display Objective | 0x4d | play | clientbound
 *
 * @param position position
 * @param name name
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Display_Objective">https://wiki.vg/Protocol#Display_Objective</a>
 */

data class ScoreboardDisplayObjectivePacket(
    val position: Byte,
    val name: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<ScoreboardDisplayObjectivePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): ScoreboardDisplayObjectivePacket {
            val position = input.readByte()
            val name = input.readString()

            return ScoreboardDisplayObjectivePacket(position, name)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: ScoreboardDisplayObjectivePacket) {
            output.writeByte(value.position)
            output.writeString(value.name)
        }
    }
}