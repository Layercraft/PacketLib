package io.layercraft.translator.packets.server.status

import io.layercraft.translator.packets.ServerPacket
import io.layercraft.translator.packets.client.status.StatusRequest
import io.layercraft.translator.serialization.processing.MinecraftString
import kotlinx.serialization.Serializable

/**
 * Status response | client-bound | Packet ID: 0x00 | State: Status | Answer to [StatusRequest]
 *
 * @property jsonResponse See Server List Ping#Response; as with all strings this is prefixed by its length as a VarInt.
 * @see <a href="https://wiki.vg/Protocol#Status_Response">https://wiki.vg/Protocol#Status_Response</a>
 */
@Serializable
data class StatusResponse(
    @MinecraftString(32767)
    val jsonResponse: String
): ServerPacket
