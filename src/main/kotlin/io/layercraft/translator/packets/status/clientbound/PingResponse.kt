package io.layercraft.translator.packets.status.clientbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Ping response | 0x01 | status | client-bound
 *
 * @property payload Long - Should be the same as sent by the client.
 * @see <a href="https://wiki.vg/Protocol#Ping_Response">https://wiki.vg/Protocol#Ping_Response</a>
 */
@MinecraftPacket(packetId = 0x01, state = PacketState.STATUS, direction = PacketDirection.CLIENTBOUND)

data class PingResponse(
    val payload: Long
) : ClientBoundPacket {
    companion object : PacketSerializer<PingResponse> {

        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): PingResponse {
            val payload = input.readLong()

            return PingResponse(payload)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PingResponse) {
            output.writeLong(value.payload)
        }
    }
}
