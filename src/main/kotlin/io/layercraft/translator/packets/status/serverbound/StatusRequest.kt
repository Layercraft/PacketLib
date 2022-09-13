package io.layercraft.translator.packets.status.client

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*

/**
 * Status request | 0x00 | status | server-bound
 *
 * @see <a href="https://wiki.vg/Protocol#Status_Request">https://wiki.vg/Protocol#Status_Request</a>
 */
@MinecraftPacket(packetId = 0x00, state = PacketState.STATUS, direction = PacketDirection.SERVERBOUND)
class StatusRequest: ServerBoundPacket {
    companion object: PacketSerializer<StatusRequest> {
        override fun serialize(input: Input): StatusRequest {
            return StatusRequest()
        }

        override fun deserialize(output: Output, value: StatusRequest) {}
    }
}
