package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 *  | 0x20 | play | serverbound
 *
 * @property id id
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Pong_.28play.29">https://wiki.vg/Protocol#Pong_.28play.29</a>
 */

@MinecraftPacket(id = 0x20, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class PongPacket(
    val id: Int,
) : ServerBoundPacket {

    companion object : PacketSerializer<PongPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): PongPacket {
            val id = input.readInt()

            return PongPacket(id)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PongPacket) {
            output.writeInt(value.id)
        }
    }
}