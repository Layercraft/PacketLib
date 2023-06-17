package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Change Difficulty | 0x02 | play | serverbound
 *
 * @param newDifficulty newDifficulty
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Change_Difficulty_2">https://wiki.vg/Protocol#Change_Difficulty_2</a>
 */

data class SetDifficultyPacket(
    val newDifficulty: UByte,
) : ServerBoundPacket {
    companion object : PacketSerializer<SetDifficultyPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SetDifficultyPacket {
            val newDifficulty = input.readUByte()

            return SetDifficultyPacket(newDifficulty)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SetDifficultyPacket) {
            output.writeUByte(value.newDifficulty)
        }
    }
}