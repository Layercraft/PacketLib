package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Lock Difficulty | 0x12 | play | serverbound
 *
 * @property locked locked
 * @see <a href="https://wiki.vg/Protocol#Lock_Difficulty">https://wiki.vg/Protocol#Lock_Difficulty</a>
 */

@MinecraftPacket(id = 0x12, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class LockDifficultyPacket(
    val locked: Boolean,
) : ServerBoundPacket {

    companion object : PacketSerializer<LockDifficultyPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): LockDifficultyPacket {
            val locked = input.readBoolean()

            return LockDifficultyPacket(locked)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: LockDifficultyPacket) {
            output.writeBoolean(value.locked)
        }
    }
}