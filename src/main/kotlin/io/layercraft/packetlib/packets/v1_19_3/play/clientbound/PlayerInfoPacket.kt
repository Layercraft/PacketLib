package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import java.util.UUID
/**
 * Player Info Update | 0x36 | play | clientbound
 *
 * @param action action
 * @param data list of PlayerInfoPacketData
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Player_Info_Update">https://wiki.vg/Protocol#Player_Info_Update</a>
 */

data class PlayerInfoPacket(
    val action: Byte,
    val data: List<PlayerInfoPacketData>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<PlayerInfoPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): PlayerInfoPacket {
            val action = input.readByte()
            val data = input.readVarIntArray { arrayInput ->
                val uuid = arrayInput.readUUID()
                val player = when (action.toInt()) {
                    else -> null
                }
                val chatSession = when (action.toInt()) {
                    else -> null
                }
                val gamemode = when (action.toInt()) {
                    4 -> arrayInput.readVarInt()
                    5 -> arrayInput.readVarInt()
                    6 -> arrayInput.readVarInt()
                    7 -> arrayInput.readVarInt()
                    12 -> arrayInput.readVarInt()
                    13 -> arrayInput.readVarInt()
                    14 -> arrayInput.readVarInt()
                    15 -> arrayInput.readVarInt()
                    20 -> arrayInput.readVarInt()
                    21 -> arrayInput.readVarInt()
                    22 -> arrayInput.readVarInt()
                    23 -> arrayInput.readVarInt()
                    28 -> arrayInput.readVarInt()
                    29 -> arrayInput.readVarInt()
                    30 -> arrayInput.readVarInt()
                    31 -> arrayInput.readVarInt()
                    36 -> arrayInput.readVarInt()
                    37 -> arrayInput.readVarInt()
                    38 -> arrayInput.readVarInt()
                    39 -> arrayInput.readVarInt()
                    44 -> arrayInput.readVarInt()
                    45 -> arrayInput.readVarInt()
                    46 -> arrayInput.readVarInt()
                    47 -> arrayInput.readVarInt()
                    52 -> arrayInput.readVarInt()
                    53 -> arrayInput.readVarInt()
                    54 -> arrayInput.readVarInt()
                    55 -> arrayInput.readVarInt()
                    60 -> arrayInput.readVarInt()
                    61 -> arrayInput.readVarInt()
                    62 -> arrayInput.readVarInt()
                    63 -> arrayInput.readVarInt()
                    else -> null
                }
                val listed = when (action.toInt()) {
                    8 -> arrayInput.readBoolean()
                    9 -> arrayInput.readBoolean()
                    10 -> arrayInput.readBoolean()
                    11 -> arrayInput.readBoolean()
                    12 -> arrayInput.readBoolean()
                    13 -> arrayInput.readBoolean()
                    14 -> arrayInput.readBoolean()
                    15 -> arrayInput.readBoolean()
                    24 -> arrayInput.readBoolean()
                    25 -> arrayInput.readBoolean()
                    26 -> arrayInput.readBoolean()
                    27 -> arrayInput.readBoolean()
                    28 -> arrayInput.readBoolean()
                    29 -> arrayInput.readBoolean()
                    30 -> arrayInput.readBoolean()
                    31 -> arrayInput.readBoolean()
                    40 -> arrayInput.readBoolean()
                    41 -> arrayInput.readBoolean()
                    42 -> arrayInput.readBoolean()
                    43 -> arrayInput.readBoolean()
                    44 -> arrayInput.readBoolean()
                    45 -> arrayInput.readBoolean()
                    46 -> arrayInput.readBoolean()
                    47 -> arrayInput.readBoolean()
                    56 -> arrayInput.readBoolean()
                    57 -> arrayInput.readBoolean()
                    58 -> arrayInput.readBoolean()
                    59 -> arrayInput.readBoolean()
                    60 -> arrayInput.readBoolean()
                    61 -> arrayInput.readBoolean()
                    62 -> arrayInput.readBoolean()
                    63 -> arrayInput.readBoolean()
                    else -> null
                }
                val latency = when (action.toInt()) {
                    16 -> arrayInput.readVarInt()
                    17 -> arrayInput.readVarInt()
                    18 -> arrayInput.readVarInt()
                    19 -> arrayInput.readVarInt()
                    20 -> arrayInput.readVarInt()
                    21 -> arrayInput.readVarInt()
                    22 -> arrayInput.readVarInt()
                    23 -> arrayInput.readVarInt()
                    24 -> arrayInput.readVarInt()
                    25 -> arrayInput.readVarInt()
                    26 -> arrayInput.readVarInt()
                    27 -> arrayInput.readVarInt()
                    28 -> arrayInput.readVarInt()
                    29 -> arrayInput.readVarInt()
                    30 -> arrayInput.readVarInt()
                    31 -> arrayInput.readVarInt()
                    48 -> arrayInput.readVarInt()
                    49 -> arrayInput.readVarInt()
                    50 -> arrayInput.readVarInt()
                    51 -> arrayInput.readVarInt()
                    52 -> arrayInput.readVarInt()
                    53 -> arrayInput.readVarInt()
                    54 -> arrayInput.readVarInt()
                    55 -> arrayInput.readVarInt()
                    56 -> arrayInput.readVarInt()
                    57 -> arrayInput.readVarInt()
                    58 -> arrayInput.readVarInt()
                    59 -> arrayInput.readVarInt()
                    60 -> arrayInput.readVarInt()
                    61 -> arrayInput.readVarInt()
                    62 -> arrayInput.readVarInt()
                    63 -> arrayInput.readVarInt()
                    else -> null
                }
                val hasDisplayName = when (action.toInt()) {
                    32 -> arrayInput.readBoolean()
                    33 -> arrayInput.readBoolean()
                    34 -> arrayInput.readBoolean()
                    35 -> arrayInput.readBoolean()
                    36 -> arrayInput.readBoolean()
                    37 -> arrayInput.readBoolean()
                    38 -> arrayInput.readBoolean()
                    39 -> arrayInput.readBoolean()
                    40 -> arrayInput.readBoolean()
                    41 -> arrayInput.readBoolean()
                    42 -> arrayInput.readBoolean()
                    43 -> arrayInput.readBoolean()
                    44 -> arrayInput.readBoolean()
                    45 -> arrayInput.readBoolean()
                    46 -> arrayInput.readBoolean()
                    47 -> arrayInput.readBoolean()
                    48 -> arrayInput.readBoolean()
                    49 -> arrayInput.readBoolean()
                    50 -> arrayInput.readBoolean()
                    51 -> arrayInput.readBoolean()
                    52 -> arrayInput.readBoolean()
                    53 -> arrayInput.readBoolean()
                    54 -> arrayInput.readBoolean()
                    55 -> arrayInput.readBoolean()
                    56 -> arrayInput.readBoolean()
                    57 -> arrayInput.readBoolean()
                    58 -> arrayInput.readBoolean()
                    59 -> arrayInput.readBoolean()
                    60 -> arrayInput.readBoolean()
                    61 -> arrayInput.readBoolean()
                    62 -> arrayInput.readBoolean()
                    63 -> arrayInput.readBoolean()
                    else -> null
                }
                val displayName = when (action.toInt()) {
                    32 -> if (hasDisplayName!!) arrayInput.readString() else null
                    33 -> if (hasDisplayName!!) arrayInput.readString() else null
                    34 -> if (hasDisplayName!!) arrayInput.readString() else null
                    35 -> if (hasDisplayName!!) arrayInput.readString() else null
                    36 -> if (hasDisplayName!!) arrayInput.readString() else null
                    37 -> if (hasDisplayName!!) arrayInput.readString() else null
                    38 -> if (hasDisplayName!!) arrayInput.readString() else null
                    39 -> if (hasDisplayName!!) arrayInput.readString() else null
                    40 -> if (hasDisplayName!!) arrayInput.readString() else null
                    41 -> if (hasDisplayName!!) arrayInput.readString() else null
                    42 -> if (hasDisplayName!!) arrayInput.readString() else null
                    43 -> if (hasDisplayName!!) arrayInput.readString() else null
                    44 -> if (hasDisplayName!!) arrayInput.readString() else null
                    45 -> if (hasDisplayName!!) arrayInput.readString() else null
                    46 -> if (hasDisplayName!!) arrayInput.readString() else null
                    47 -> if (hasDisplayName!!) arrayInput.readString() else null
                    48 -> if (hasDisplayName!!) arrayInput.readString() else null
                    49 -> if (hasDisplayName!!) arrayInput.readString() else null
                    50 -> if (hasDisplayName!!) arrayInput.readString() else null
                    51 -> if (hasDisplayName!!) arrayInput.readString() else null
                    52 -> if (hasDisplayName!!) arrayInput.readString() else null
                    53 -> if (hasDisplayName!!) arrayInput.readString() else null
                    54 -> if (hasDisplayName!!) arrayInput.readString() else null
                    55 -> if (hasDisplayName!!) arrayInput.readString() else null
                    56 -> if (hasDisplayName!!) arrayInput.readString() else null
                    57 -> if (hasDisplayName!!) arrayInput.readString() else null
                    58 -> if (hasDisplayName!!) arrayInput.readString() else null
                    59 -> if (hasDisplayName!!) arrayInput.readString() else null
                    60 -> if (hasDisplayName!!) arrayInput.readString() else null
                    61 -> if (hasDisplayName!!) arrayInput.readString() else null
                    62 -> if (hasDisplayName!!) arrayInput.readString() else null
                    63 -> if (hasDisplayName!!) arrayInput.readString() else null
                    else -> null
                }

                PlayerInfoPacketData(uuid, gamemode, listed, latency, hasDisplayName, displayName)
            }

            return PlayerInfoPacket(action, data)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: PlayerInfoPacket) {
            output.writeByte(value.action)

            output.writeVarIntArray(value.data) { arrayValue, arrayOutput ->
                arrayOutput.writeUUID(arrayValue.uuid)
                when (value.action.toInt()) {
                    else -> {}
                }
                when (value.action.toInt()) {
                    else -> {}
                }
                when (value.action.toInt()) {
                    4 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    5 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    6 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    7 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    12 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    13 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    14 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    15 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    20 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    21 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    22 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    23 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    28 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    29 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    30 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    31 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    36 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    37 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    38 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    39 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    44 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    45 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    46 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    47 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    52 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    53 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    54 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    55 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    60 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    61 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    62 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    63 -> arrayOutput.writeVarInt(arrayValue.gamemode!!)
                    else -> {}
                }
                when (value.action.toInt()) {
                    8 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    9 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    10 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    11 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    12 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    13 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    14 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    15 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    24 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    25 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    26 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    27 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    28 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    29 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    30 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    31 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    40 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    41 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    42 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    43 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    44 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    45 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    46 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    47 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    56 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    57 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    58 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    59 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    60 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    61 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    62 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    63 -> arrayOutput.writeBoolean(arrayValue.listed!!)
                    else -> {}
                }
                when (value.action.toInt()) {
                    16 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    17 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    18 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    19 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    20 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    21 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    22 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    23 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    24 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    25 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    26 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    27 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    28 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    29 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    30 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    31 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    48 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    49 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    50 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    51 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    52 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    53 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    54 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    55 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    56 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    57 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    58 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    59 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    60 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    61 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    62 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    63 -> arrayOutput.writeVarInt(arrayValue.latency!!)
                    else -> {}
                }
                when (value.action.toInt()) {
                    32 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    33 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    34 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    35 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    36 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    37 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    38 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    39 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    40 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    41 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    42 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    43 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    44 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    45 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    46 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    47 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    48 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    49 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    50 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    51 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    52 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    53 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    54 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    55 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    56 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    57 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    58 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    59 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    60 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    61 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    62 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    63 -> arrayOutput.writeBoolean(arrayValue.hasDisplayName!!)
                    else -> {}
                }
                when (value.action.toInt()) {
                    32 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    33 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    34 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    35 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    36 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    37 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    38 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    39 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    40 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    41 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    42 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    43 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    44 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    45 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    46 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    47 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    48 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    49 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    50 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    51 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    52 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    53 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    54 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    55 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    56 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    57 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    58 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    59 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    60 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    61 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    62 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    63 -> if (arrayValue.hasDisplayName!!) arrayOutput.writeString(arrayValue.displayName!!)
                    else -> {}
                }
            }
        }
    }
}

/**
 * PlayerInfoPacketData
 *
 * @param uuid uuid
 * @param gamemode gamemode
 * @param listed listed
 * @param latency latency
 * @param hasDisplayName hasDisplayName
 * @param displayName displayName
*/
data class PlayerInfoPacketData(
    val uuid: UUID,
    val gamemode: Int?, // varint
    val listed: Boolean?,
    val latency: Int?, // varint
    val hasDisplayName: Boolean?,
    val displayName: String?,
)