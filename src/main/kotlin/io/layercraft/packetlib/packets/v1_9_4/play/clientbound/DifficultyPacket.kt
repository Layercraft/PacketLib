package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x0d | play | clientbound
 *
 * @property difficulty difficulty
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x0d, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class DifficultyPacket(
    val difficulty: UByte,
) : ClientBoundPacket {

    companion object : PacketSerializer<DifficultyPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): DifficultyPacket {
            val difficulty = input.readUByte()

            return DifficultyPacket(difficulty)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: DifficultyPacket) {
            output.writeUByte(value.difficulty)
        }
    }
}
