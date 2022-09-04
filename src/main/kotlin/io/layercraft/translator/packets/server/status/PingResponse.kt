package io.layercraft.translator.packets.server.status

import io.layercraft.translator.MinecraftNumber
import io.layercraft.translator.MinecraftNumberType
import io.layercraft.translator.packets.ServerPacket
import kotlinx.serialization.Serializable
import io.layercraft.translator.packets.client.status.PingRequest

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
