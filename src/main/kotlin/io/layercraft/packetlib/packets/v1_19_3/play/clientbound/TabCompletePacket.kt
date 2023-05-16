package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Command Suggestions Response | 0x0d | play | clientbound
 *
 * @param transactionId transactionId
 * @param start start
 * @param length length
 * @param matches list of TabCompletePacketMatches
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18071#Command_Suggestions_Response">https://wiki.vg/Protocol#Command_Suggestions_Response</a>
 */

@MinecraftPacket(id = 0x0d, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class TabCompletePacket(
    val transactionId: Int, // varint
    val start: Int, // varint
    val length: Int, // varint
    val matches: List<TabCompletePacketMatches>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<TabCompletePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): TabCompletePacket {
            val transactionId = input.readVarInt()
            val start = input.readVarInt()
            val length = input.readVarInt()
            val matches = input.readVarIntArray { arrayInput ->
                val match = arrayInput.readString()
                val hasTooltip = arrayInput.readBoolean()
                val tooltip = if (hasTooltip) arrayInput.readString() else null

                return@readVarIntArray TabCompletePacketMatches(match, hasTooltip, tooltip)
            }

            return TabCompletePacket(transactionId, start, length, matches)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: TabCompletePacket) {
            output.writeVarInt(value.transactionId)
            output.writeVarInt(value.start)
            output.writeVarInt(value.length)

            output.writeVarIntArray(value.matches) { arrayValue, arrayOutput ->
                arrayOutput.writeString(arrayValue.match)
                arrayOutput.writeBoolean(arrayValue.hasTooltip)
                if (arrayValue.hasTooltip) arrayOutput.writeString(arrayValue.tooltip!!)
            }
        }
    }
}

/**
 * TabCompletePacketMatches
 *
 * @param match match
 * @param hasTooltip tooltip is present
 * @param tooltip tooltip
*/
data class TabCompletePacketMatches(
    val match: String,
    val hasTooltip: Boolean,
    val tooltip: String?,
)