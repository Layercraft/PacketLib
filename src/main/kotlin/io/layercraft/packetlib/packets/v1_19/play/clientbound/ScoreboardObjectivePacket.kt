package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Objectives | 0x53 | play | clientbound
 *
 * @property name name
 * @property action action
 * @see <a href="https://wiki.vg/Protocol#Update_Objectives">https://wiki.vg/Protocol#Update_Objectives</a>
 */

@MinecraftPacket(packetId = 0x53, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ScoreboardObjectivePacket(
    val name: String,
    val action: Byte,
) : ClientBoundPacket {

    companion object : PacketSerializer<ScoreboardObjectivePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ScoreboardObjectivePacket {
            val name = input.readString()
            val action = input.readByte()

            return ScoreboardObjectivePacket(name, action)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ScoreboardObjectivePacket) {
            output.writeString(value.name)
            output.writeByte(value.action)
        }
    }
}