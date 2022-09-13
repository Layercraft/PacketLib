package io.layercraft.translator.packets.status.serverbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*

/**
 * Ping request | 0x01 | status | server-bound
 *
 * @property payload Long - Maybe any number. Notchian clients use a system-dependent time value which is counted in milliseconds.

 * @see <a href="https://wiki.vg/Protocol#Ping_Request">https://wiki.vg/Protocol#Ping_Request</a>
 */

@MinecraftPacket(packetId = 0x01, state = PacketState.STATUS, direction = PacketDirection.SERVERBOUND)
data class PingRequest(
    val payload: Long
): ServerBoundPacket {
    companion object: PacketSerializer<PingRequest> {
        override fun serialize(input: Input): PingRequest {
            val payload = input.readLong()
            return PingRequest(payload)
        }

        override fun deserialize(output: Output, value: PingRequest) {
            output.writeLong(value.payload)
        }
    }
}
