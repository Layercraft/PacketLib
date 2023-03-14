package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Pong (play) | 0x1f | play | serverbound
 *
 * @param id id
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Pong_.28play.29">https://wiki.vg/Protocol#Pong_.28play.29</a>
 */

@MinecraftPacket(id = 0x1f, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class PongPacket(
    val id: Int,
) : ServerBoundPacket {
    companion object : PacketSerializer<PongPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): PongPacket {
            val id = input.readInt()

            return PongPacket(id)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: PongPacket) {
            output.writeInt(value.id)
        }
    }
}