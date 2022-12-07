package io.layercraft.translator.packets.play.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Command Suggestions Response
 *
 * The server responds with a list of auto-completions of the last word sent to it. In the case of regular chat, this is a player username. Command names and parameters are also supported. The client sorts these alphabetically before listing them.
 *
 * @property id Transaction ID.
 * @property start Start of the text to replace.
 * @property length Length of the text to replace.
 * @property matches [CommandSuggestionsResponseMatch]
 * @constructor Create empty Command suggestions response packet
 */
@MinecraftPacket(0x0E, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class CommandSuggestionsResponsePacket(
    val id: Int, // varint
    val start: Int, // varint
    val length: Int, // varint
    val matches: List<CommandSuggestionsResponseMatch>, // varint array
): ClientBoundPacket {
    companion object: PacketSerializer<CommandSuggestionsResponsePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): CommandSuggestionsResponsePacket {
            val id = input.readVarInt()
            val start = input.readVarInt()
            val length = input.readVarInt()
            val matches = input.readVarIntArray {
                val match = input.readString()
                val hasToolTip = input.readBoolean()
                val toolTip = if (hasToolTip) input.readChat() else null

                CommandSuggestionsResponseMatch(match, hasToolTip, toolTip)
            }

            return CommandSuggestionsResponsePacket(id, start, length, matches)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: CommandSuggestionsResponsePacket) {
            output.writeVarInt(value.id)
            output.writeVarInt(value.start)
            output.writeVarInt(value.length)
            output.writeVarIntArray(value.matches) { forValue, forOutput ->
                forOutput.writeString(forValue.match)
                forOutput.writeBoolean(forValue.hasToolTip)
                if (forValue.hasToolTip) forOutput.writeChat(forValue.toolTip!!)
            }
        }

    }
}

/**
 * Command suggestions response match
 *
 * @property match One eligible value to insert, note that each command is sent separately instead of in a single string, hence the need for Count. Note that for instance this doesn't include a leading / on commands.
 * @property hasToolTip True if the following is present.
 * @property toolTip Tooltip to display; only present if previous boolean is true.
 * @constructor Create empty Command suggestions response packet match
 */
data class CommandSuggestionsResponseMatch(
    val match: String, // String (32767)
    val hasToolTip: Boolean,
    val toolTip: String?, // Chat
)
