package io.layercraft.translator.packets.status.serverbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Status request | 0x00 | status | server-bound
 *
 * @see <a href="https://wiki.vg/Protocol#Status_Request">https://wiki.vg/Protocol#Status_Request</a>
 */
@MinecraftPacket(packetId = 0x00, state = PacketState.STATUS, direction = PacketDirection.SERVERBOUND)
class StatusRequest: ServerBoundPacket {
    companion object: PacketSerializer<StatusRequest> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): StatusRequest {
            return StatusRequest()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: StatusRequest) {}
    }
}
