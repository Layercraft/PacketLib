package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Score | 0x59 | play | clientbound
 *
 * @property itemName itemName
 * @property action action
 * @property scoreName scoreName
 * @property value value
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Update_Score">https://wiki.vg/Protocol#Update_Score</a>
 */

@MinecraftPacket(id = 0x59, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ScoreboardScorePacket(
    val itemName: String,
    val action: Int, // varint
    val scoreName: String,
    val value: Int?, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<ScoreboardScorePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ScoreboardScorePacket {
            val itemName = input.readString()
            val action = input.readVarInt()
            val scoreName = input.readString()
            val value = when (action) {
                1 -> null
                else -> input.readVarInt()
            }

            return ScoreboardScorePacket(itemName, action, scoreName, value)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ScoreboardScorePacket) {
            output.writeString(value.itemName)
            output.writeVarInt(value.action)
            output.writeString(value.scoreName)
            when (value.action) {
                else -> output.writeVarInt(value.value!!)
            }
        }
    }
}