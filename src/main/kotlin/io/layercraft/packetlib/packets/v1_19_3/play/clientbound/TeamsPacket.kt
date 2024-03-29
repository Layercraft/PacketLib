package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Update Teams | 0x56 | play | clientbound
 *
 * @param team team
 * @param mode mode
 * @param name name
 * @param friendlyFire friendlyFire
 * @param nameTagVisibility nameTagVisibility
 * @param collisionRule collisionRule
 * @param formatting formatting
 * @param prefix prefix
 * @param suffix suffix
 * @param players players
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Teams">https://wiki.vg/Protocol#Update_Teams</a>
 */

data class TeamsPacket(
    val team: String,
    val mode: Byte,
    val name: String?,
    val friendlyFire: Byte?,
    val nameTagVisibility: String?,
    val collisionRule: String?,
    val formatting: Int?, // varint
    val prefix: String?,
    val suffix: String?,
    val players: List<String>?,
) : ClientBoundPacket {
    companion object : PacketSerializer<TeamsPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): TeamsPacket {
            val team = input.readString()
            val mode = input.readByte()
            val name = when (mode.toInt()) {
                0 -> input.readString()
                2 -> input.readString()
                else -> null
            }
            val friendlyFire = when (mode.toInt()) {
                0 -> input.readByte()
                2 -> input.readByte()
                else -> null
            }
            val nameTagVisibility = when (mode.toInt()) {
                0 -> input.readString()
                2 -> input.readString()
                else -> null
            }
            val collisionRule = when (mode.toInt()) {
                0 -> input.readString()
                2 -> input.readString()
                else -> null
            }
            val formatting = when (mode.toInt()) {
                0 -> input.readVarInt()
                2 -> input.readVarInt()
                else -> null
            }
            val prefix = when (mode.toInt()) {
                0 -> input.readString()
                2 -> input.readString()
                else -> null
            }
            val suffix = when (mode.toInt()) {
                0 -> input.readString()
                2 -> input.readString()
                else -> null
            }
            val players = when (mode.toInt()) {
                0 -> input.readVarIntArray { arrayInput -> arrayInput.readString() }
                3 -> input.readVarIntArray { arrayInput -> arrayInput.readString() }
                4 -> input.readVarIntArray { arrayInput -> arrayInput.readString() }
                else -> null
            }

            return TeamsPacket(team, mode, name, friendlyFire, nameTagVisibility, collisionRule, formatting, prefix, suffix, players)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: TeamsPacket) {
            output.writeString(value.team)
            output.writeByte(value.mode)
            when (value.mode.toInt()) {
                0 -> output.writeString(value.name!!)
                2 -> output.writeString(value.name!!)
                else -> {}
            }
            when (value.mode.toInt()) {
                0 -> output.writeByte(value.friendlyFire!!)
                2 -> output.writeByte(value.friendlyFire!!)
                else -> {}
            }
            when (value.mode.toInt()) {
                0 -> output.writeString(value.nameTagVisibility!!)
                2 -> output.writeString(value.nameTagVisibility!!)
                else -> {}
            }
            when (value.mode.toInt()) {
                0 -> output.writeString(value.collisionRule!!)
                2 -> output.writeString(value.collisionRule!!)
                else -> {}
            }
            when (value.mode.toInt()) {
                0 -> output.writeVarInt(value.formatting!!)
                2 -> output.writeVarInt(value.formatting!!)
                else -> {}
            }
            when (value.mode.toInt()) {
                0 -> output.writeString(value.prefix!!)
                2 -> output.writeString(value.prefix!!)
                else -> {}
            }
            when (value.mode.toInt()) {
                0 -> output.writeString(value.suffix!!)
                2 -> output.writeString(value.suffix!!)
                else -> {}
            }
            when (value.mode.toInt()) {
                0 -> output.writeVarIntArray(value.players!!) { arrayValue, arrayOutput -> arrayOutput.writeString(arrayValue) }
                3 -> output.writeVarIntArray(value.players!!) { arrayValue, arrayOutput -> arrayOutput.writeString(arrayValue) }
                4 -> output.writeVarIntArray(value.players!!) { arrayValue, arrayOutput -> arrayOutput.writeString(arrayValue) }
                else -> {}
            }
        }
    }
}