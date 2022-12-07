package io.layercraft.translator.packets.play.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.packets.play.data.Difficulty
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Change difficulty | 0x0B | play | clientbound
 *
 * @property difficulty 0: peaceful, 1: easy, 2: normal, 3: hard.
 * @property locked Difficulty locked?
 * @see <a href="https://wiki.vg/Protocol#Change_Difficulty">https://wiki.vg/Protocol#Change_Difficulty</a>
 */
@MinecraftPacket(0x0B, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class ChangeDifficultyPacket(
    val difficulty: Difficulty, // Unsigned byte
    val locked: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<ChangeDifficultyPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ChangeDifficultyPacket {
            val difficulty = Difficulty.byId(input.readVarInt())!!
            val locked = input.readBoolean()

            return ChangeDifficultyPacket(difficulty, locked)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ChangeDifficultyPacket) {
            output.writeVarInt(value.difficulty.id)
            output.writeBoolean(value.locked)
        }
    }
}