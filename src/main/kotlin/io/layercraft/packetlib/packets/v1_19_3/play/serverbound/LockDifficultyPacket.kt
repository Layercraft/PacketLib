package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Lock Difficulty | 0x12 | play | serverbound
 *
 * @param locked locked
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Lock_Difficulty">https://wiki.vg/Protocol#Lock_Difficulty</a>
 */

@MinecraftPacket(id = 0x12, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class LockDifficultyPacket(
    val locked: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<LockDifficultyPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): LockDifficultyPacket {
            val locked = input.readBoolean()

            return LockDifficultyPacket(locked)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: LockDifficultyPacket) {
            output.writeBoolean(value.locked)
        }
    }
}