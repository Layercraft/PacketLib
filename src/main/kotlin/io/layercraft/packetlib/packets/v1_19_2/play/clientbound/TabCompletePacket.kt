package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Command Suggestions Response | 0x0e | play | clientbound
 *
 * @property transactionId transactionId
 * @property start start
 * @property length length
 * @property matches matches
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Command_Suggestions_Response">https://wiki.vg/Protocol#Command_Suggestions_Response</a>
 */

@MinecraftPacket(id = 0x0e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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
 * TabCompletePacketMatches | matches
 *
 * @property match match
 * @property hasTooltip tooltip is present
 * @property tooltip tooltip
*/
data class TabCompletePacketMatches(
    val match: String,
    val hasTooltip: Boolean,
    val tooltip: String?,
)
