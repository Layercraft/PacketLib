package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Update Objectives | 0x54 | play | clientbound
 *
 * @param name name
 * @param action action
 * @param displayText displayText
 * @param type type
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Objectives">https://wiki.vg/Protocol#Update_Objectives</a>
 */

data class ScoreboardObjectivePacket(
    val name: String,
    val action: Byte,
    val displayText: String?,
    val type: Int?, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<ScoreboardObjectivePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): ScoreboardObjectivePacket {
            val name = input.readString()
            val action = input.readByte()
            val displayText = when (action.toInt()) {
                0 -> input.readString()
                2 -> input.readString()
                else -> null
            }
            val type = when (action.toInt()) {
                0 -> input.readVarInt()
                2 -> input.readVarInt()
                else -> null
            }

            return ScoreboardObjectivePacket(name, action, displayText, type)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: ScoreboardObjectivePacket) {
            output.writeString(value.name)
            output.writeByte(value.action)
            when (value.action.toInt()) {
                0 -> output.writeString(value.displayText!!)
                2 -> output.writeString(value.displayText!!)
                else -> {}
            }
            when (value.action.toInt()) {
                0 -> output.writeVarInt(value.type!!)
                2 -> output.writeVarInt(value.type!!)
                else -> {}
            }
        }
    }
}