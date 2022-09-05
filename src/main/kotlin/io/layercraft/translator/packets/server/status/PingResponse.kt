package io.layercraft.translator.packets.server.status

import io.layercraft.translator.packets.ServerPacket
import io.layercraft.translator.packets.client.status.PingRequest
import io.layercraft.translator.serialization.processing.MinecraftNumber
import io.layercraft.translator.serialization.processing.MinecraftNumberType
import kotlinx.serialization.Serializable

/**
 * Ping response | client-bound | Packet ID: 0x01 | State: Status | Answer to [PingRequest]
 *
 * @property payload Should be the same as sent by the client.
 * @see <a href="https://wiki.vg/Protocol#Ping_Response">https://wiki.vg/Protocol#Ping_Response</a>
 */
@Serializable
data class PingResponse(
    @MinecraftNumber(MinecraftNumberType.DEFAULT)
    val payload: Long
) : ServerPacket
