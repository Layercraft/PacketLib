package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Change Difficulty | 0x0b | play | clientbound
 *
 * @param difficulty difficulty
 * @param difficultyLocked difficultyLocked
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Change_Difficulty">https://wiki.vg/Protocol#Change_Difficulty</a>
 */

data class DifficultyPacket(
    val difficulty: UByte,
    val difficultyLocked: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<DifficultyPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): DifficultyPacket {
            val difficulty = input.readUByte()
            val difficultyLocked = input.readBoolean()

            return DifficultyPacket(difficulty, difficultyLocked)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: DifficultyPacket) {
            output.writeUByte(value.difficulty)
            output.writeBoolean(value.difficultyLocked)
        }
    }
}