package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Change Difficulty | 0x0b | play | clientbound
 *
 * @property difficulty difficulty
 * @property difficultyLocked difficultyLocked
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Change_Difficulty">https://wiki.vg/Protocol#Change_Difficulty</a>
 */

@MinecraftPacket(id = 0x0b, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class DifficultyPacket(
    val difficulty: UByte,
    val difficultyLocked: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<DifficultyPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): DifficultyPacket {
            val difficulty = input.readUByte()
            val difficultyLocked = input.readBoolean()

            return DifficultyPacket(difficulty, difficultyLocked)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: DifficultyPacket) {
            output.writeUByte(value.difficulty)
            output.writeBoolean(value.difficultyLocked)
        }
    }
}