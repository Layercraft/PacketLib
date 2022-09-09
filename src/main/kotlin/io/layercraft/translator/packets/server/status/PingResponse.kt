package io.layercraft.translator.packets.server.status

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*

/**
 * Ping response | 0x01 | status | client-bound
 *
 * @property payload Long - Should be the same as sent by the client.
 * @see <a href="https://wiki.vg/Protocol#Ping_Response">https://wiki.vg/Protocol#Ping_Response</a>
 */
@MinecraftPacket(packetId = 0x01, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)

data class PingResponse(
    val payload: Long
) : ServerPacket {
    companion object : PacketSerializer<PingResponse> {

        override fun serialize(input: Input): PingResponse {
            val payload = input.readLong()

            return PingResponse(payload)
        }

        override fun deserialize(output: Output, value: PingResponse) {
            output.writeLong(value.payload)
        }
    }
}
