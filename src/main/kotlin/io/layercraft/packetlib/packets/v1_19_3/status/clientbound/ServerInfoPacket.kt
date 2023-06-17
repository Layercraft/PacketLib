package io.layercraft.packetlib.packets.v1_19_3.status.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Status Response | 0x00 | status | clientbound
 *
 * @param response response
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Status_Response">https://wiki.vg/Protocol#Status_Response</a>
 */

data class ServerInfoPacket(
    val response: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<ServerInfoPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): ServerInfoPacket {
            val response = input.readString()

            return ServerInfoPacket(response)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: ServerInfoPacket) {
            output.writeString(value.response)
        }
    }
}