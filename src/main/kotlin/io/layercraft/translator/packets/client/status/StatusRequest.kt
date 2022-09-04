package io.layercraft.translator.packets.client.status

import io.layercraft.translator.packets.ClientPacket
import kotlinx.serialization.Serializable
import io.layercraft.translator.packets.server.status.StatusResponse

/**
 * Status request | server-bound | Packet ID: 0x00 | State: Status | Answers with [StatusResponse].
 *
 * @constructor Create Status request
 * @see <a href="https://wiki.vg/Protocol#Status_Request">https://wiki.vg/Protocol#Status_Request</a>
 */
@Serializable
class StatusRequest: ClientPacket
