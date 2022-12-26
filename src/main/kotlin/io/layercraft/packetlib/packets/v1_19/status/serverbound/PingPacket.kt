package io.layercraft.packetlib.packets.v1_19.status.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Ping Request | 0x01 | status | serverbound
 *
 * @property time time
 * @see <a href="https://wiki.vg/Protocol#Ping_Request">https://wiki.vg/Protocol#Ping_Request</a>
 */

@MinecraftPacket(packetId = 0x01, state = PacketState.STATUS, direction = PacketDirection.SERVERBOUND)
data class PingPacket(
    val time: Long,
) : ServerBoundPacket {

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