package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Change Difficulty | 0x02 | play | serverbound
 *
 * @property newDifficulty newDifficulty
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Change_Difficulty_2">https://wiki.vg/Protocol#Change_Difficulty_2</a>
 */

@MinecraftPacket(id = 0x02, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class SetDifficultyPacket(
    val newDifficulty: UByte,
) : ServerBoundPacket {
    companion object : PacketSerializer<SetDifficultyPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SetDifficultyPacket {
            val newDifficulty = input.readUByte()

            return SetDifficultyPacket(newDifficulty)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SetDifficultyPacket) {
            output.writeUByte(value.newDifficulty)
        }
    }
}