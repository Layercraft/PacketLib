package io.layercraft.packetlib.packets.v1_19_3.status.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Ping Response | 0x01 | status | clientbound
 *
 * @param time time
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Ping_Response">https://wiki.vg/Protocol#Ping_Response</a>
 */

data class PingPacket(
    val time: Long,
) : ClientBoundPacket {
    companion object : PacketSerializer<PingPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): PingPacket {
            val time = input.readLong()

            return PingPacket(time)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: PingPacket) {
            output.writeLong(value.time)
        }
    }
}