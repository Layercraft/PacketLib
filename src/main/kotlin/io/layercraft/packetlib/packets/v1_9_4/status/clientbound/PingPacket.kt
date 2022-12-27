package io.layercraft.packetlib.packets.v1_9_4.status.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Pong | 0x01 | status | clientbound
 *
 * @property time time
 * @see <a href="https://wiki.vg/Protocol#Pong">https://wiki.vg/Protocol#Pong</a>
 */

@MinecraftPacket(id = 0x01, state = PacketState.STATUS, direction = PacketDirection.CLIENTBOUND)
data class PingPacket(
    val time: Long,
) : ClientBoundPacket {

    companion object : PacketSerializer<PingPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): PingPacket {
            val time = input.readLong()

            return PingPacket(time)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PingPacket) {
            output.writeLong(value.time)
        }
    }
}
