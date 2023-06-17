package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Lock Difficulty | 0x12 | play | serverbound
 *
 * @param locked locked
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Lock_Difficulty">https://wiki.vg/Protocol#Lock_Difficulty</a>
 */

data class LockDifficultyPacket(
    val locked: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<LockDifficultyPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): LockDifficultyPacket {
            val locked = input.readBoolean()

            return LockDifficultyPacket(locked)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: LockDifficultyPacket) {
            output.writeBoolean(value.locked)
        }
    }
}