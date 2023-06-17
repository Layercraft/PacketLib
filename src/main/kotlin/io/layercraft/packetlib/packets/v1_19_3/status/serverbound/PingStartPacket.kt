package io.layercraft.packetlib.packets.v1_19_3.status.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Status Request | 0x00 | status | serverbound
 *

 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Status_Request">https://wiki.vg/Protocol#Status_Request</a>
 */

class PingStartPacket() : ServerBoundPacket {
    companion object : PacketSerializer<PingStartPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): PingStartPacket {
            return PingStartPacket()
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: PingStartPacket) {
        }
    }
}