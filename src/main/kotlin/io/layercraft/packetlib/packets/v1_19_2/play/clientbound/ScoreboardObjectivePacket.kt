package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Objectives | 0x56 | play | clientbound
 *
 * @param name name
 * @param action action
 * @param displayText displayText
 * @param type type
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Update_Objectives">https://wiki.vg/Protocol#Update_Objectives</a>
 */

@MinecraftPacket(id = 0x56, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ScoreboardObjectivePacket(
    val name: String,
    val action: Byte,
    val displayText: String?,
    val type: Int?, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<ScoreboardObjectivePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ScoreboardObjectivePacket {
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

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ScoreboardObjectivePacket) {
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