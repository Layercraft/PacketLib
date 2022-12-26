package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x0b | play | clientbound
 *
 * @property difficulty difficulty
 * @property difficultyLocked difficultyLocked
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x0b, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class DifficultyPacket(
    val difficulty: UByte,
    val difficultyLocked: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<DifficultyPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): DifficultyPacket {
            val difficulty = input.readUByte()
            val difficultyLocked = input.readBoolean()

            return DifficultyPacket(difficulty, difficultyLocked)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: DifficultyPacket) {
            output.writeUByte(value.difficulty)
            output.writeBoolean(value.difficultyLocked)
        }
    }
}