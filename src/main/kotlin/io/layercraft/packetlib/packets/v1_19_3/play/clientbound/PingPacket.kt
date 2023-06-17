package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Ping (play) | 0x2e | play | clientbound
 *
 * @param id id
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Ping_.28play.29">https://wiki.vg/Protocol#Ping_.28play.29</a>
 */

data class PingPacket(
    val id: Int,
) : ClientBoundPacket {
    companion object : PacketSerializer<PingPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): PingPacket {
            val id = input.readInt()

            return PingPacket(id)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: PingPacket) {
            output.writeInt(value.id)
        }
    }
}