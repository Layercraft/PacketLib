package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Keep Alive | 0x11 | play | serverbound
 *
 * @param keepAliveId keepAliveId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Keep_Alive_2">https://wiki.vg/Protocol#Keep_Alive_2</a>
 */

data class KeepAlivePacket(
    val keepAliveId: Long,
) : ServerBoundPacket {
    companion object : PacketSerializer<KeepAlivePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): KeepAlivePacket {
            val keepAliveId = input.readLong()

            return KeepAlivePacket(keepAliveId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: KeepAlivePacket) {
            output.writeLong(value.keepAliveId)
        }
    }
}