package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Score | 0x56 | play | clientbound
 *
 * @property itemName itemName
 * @property action action
 * @property scoreName scoreName
 * @see <a href="https://wiki.vg/Protocol#Update_Score">https://wiki.vg/Protocol#Update_Score</a>
 */

@MinecraftPacket(packetId = 0x56, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ScoreboardScorePacket(
    val itemName: String,
    val action: Int, // varint
    val scoreName: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<ScoreboardScorePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ScoreboardScorePacket {
            val itemName = input.readString()
            val action = input.readVarInt()
            val scoreName = input.readString()

            return ScoreboardScorePacket(itemName, action, scoreName)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ScoreboardScorePacket) {
            output.writeString(value.itemName)
            output.writeVarInt(value.action)
            output.writeString(value.scoreName)
        }
    }
}