package io.layercraft.translator.packets.client.status

import io.layercraft.translator.packets.ClientPacket
import io.layercraft.translator.packets.server.status.PingResponse
import io.layercraft.translator.serialization.processing.MinecraftNumber
import io.layercraft.translator.serialization.processing.MinecraftNumberType
import kotlinx.serialization.Serializable

/**
 * Ping request | server-bound | Packet ID: 0x01 | State: Status | Answers with [PingResponse].
 *
 * @property payload Maybe any number. Notchian clients use a system-dependent time value which is counted in milliseconds.

 * @see <a href="https://wiki.vg/Protocol#Ping_Request">https://wiki.vg/Protocol#Ping_Request</a>
 */
@Serializable
data class PingRequest(
    @MinecraftNumber(MinecraftNumberType.DEFAULT)
    val payload: Long
): ClientPacket
