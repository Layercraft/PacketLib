package io.layercraft.translator.packets.status.serverbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Ping request | 0x01 | status | server-bound
 *
 * @property payload Maybe any number. Notchian clients use a system-dependent time value which is counted in milliseconds.

 * @see <a href="https://wiki.vg/Protocol#Ping_Request">https://wiki.vg/Protocol#Ping_Request</a>
 */

@MinecraftPacket(packetId = 0x01, state = PacketState.STATUS, direction = PacketDirection.SERVERBOUND)
data class PingRequestPacket(
    val payload: Long,
) : ServerBoundPacket {
    companion object : PacketSerializer<PingRequestPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): PingRequestPacket {
            val payload = input.readLong()
            return PingRequestPacket(payload)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PingRequestPacket) {
            output.writeLong(value.payload)
        }
    }
}