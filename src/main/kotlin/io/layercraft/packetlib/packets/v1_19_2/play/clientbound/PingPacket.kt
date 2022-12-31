package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Ping (play) | 0x2f | play | clientbound
 *
 * @property id id
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Ping_.28play.29">https://wiki.vg/Protocol#Ping_.28play.29</a>
 */

@MinecraftPacket(id = 0x2f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class PingPacket(
    val id: Int,
) : ClientBoundPacket {
    companion object : PacketSerializer<PingPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): PingPacket {
            val id = input.readInt()

            return PingPacket(id)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: PingPacket) {
            output.writeInt(value.id)
        }
    }
}
