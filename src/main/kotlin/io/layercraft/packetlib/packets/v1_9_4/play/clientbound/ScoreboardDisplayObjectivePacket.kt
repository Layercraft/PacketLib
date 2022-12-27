package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Display Scoreboard | 0x38 | play | clientbound
 *
 * @property position position
 * @property name name
 * @see <a href="https://wiki.vg/Protocol#Display_Scoreboard">https://wiki.vg/Protocol#Display_Scoreboard</a>
 */

@MinecraftPacket(id = 0x38, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ScoreboardDisplayObjectivePacket(
    val position: Byte,
    val name: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<ScoreboardDisplayObjectivePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ScoreboardDisplayObjectivePacket {
            val position = input.readByte()
            val name = input.readString()

            return ScoreboardDisplayObjectivePacket(position, name)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ScoreboardDisplayObjectivePacket) {
            output.writeByte(value.position)
            output.writeString(value.name)
        }
    }
}
