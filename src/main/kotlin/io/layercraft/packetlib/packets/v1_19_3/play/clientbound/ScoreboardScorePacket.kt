package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Update Score | 0x57 | play | clientbound
 *
 * @param itemName itemName
 * @param action action
 * @param scoreName scoreName
 * @param value value
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Score">https://wiki.vg/Protocol#Update_Score</a>
 */

data class ScoreboardScorePacket(
    val itemName: String,
    val action: Int, // varint
    val scoreName: String,
    val value: Int?, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<ScoreboardScorePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): ScoreboardScorePacket {
            val itemName = input.readString()
            val action = input.readVarInt()
            val scoreName = input.readString()
            val value = when (action) {
                1 -> null
                else -> input.readVarInt()
            }

            return ScoreboardScorePacket(itemName, action, scoreName, value)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: ScoreboardScorePacket) {
            output.writeString(value.itemName)
            output.writeVarInt(value.action)
            output.writeString(value.scoreName)
            when (value.action) {
                else -> output.writeVarInt(value.value!!)
            }
        }
    }
}