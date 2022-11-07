package io.layercraft.translator.packets.status.clientbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc

/**
 * Status response | 0x00 | status | client-bound
 *
 * @property jsonResponse String (32767) - See Server List Ping#Response; as with all strings this is prefixed by its length as a VarInt.
 * @see <a href="https://wiki.vg/Protocol#Status_Response">https://wiki.vg/Protocol#Status_Response</a>
 */
@MinecraftPacket(packetId = 0x00, state = PacketState.STATUS, direction = PacketDirection.CLIENTBOUND)
data class StatusResponse(
    val jsonResponse: String
): ClientBoundPacket {
    companion object: PacketSerializer<StatusResponse> {

        override fun serialize(input: Input): StatusResponse {
            val jsonResponse = input.mc.readString(32767)

            return StatusResponse(jsonResponse)
        }

        override fun deserialize(output: Output, value: StatusResponse) {
            output.mc.writeString(value.jsonResponse, 32767)
        }
    }
}
