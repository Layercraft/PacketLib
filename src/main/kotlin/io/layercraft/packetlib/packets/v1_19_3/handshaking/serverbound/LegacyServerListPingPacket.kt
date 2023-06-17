package io.layercraft.packetlib.packets.v1_19_3.handshaking.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Legacy Server List Ping | 0xfe | handshaking | serverbound
 *
 * @param payload payload
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Legacy_Server_List_Ping">https://wiki.vg/Protocol#Legacy_Server_List_Ping</a>
 */

data class LegacyServerListPingPacket(
    val payload: UByte,
) : ServerBoundPacket {
    companion object : PacketSerializer<LegacyServerListPingPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): LegacyServerListPingPacket {
            val payload = input.readUByte()

            return LegacyServerListPingPacket(payload)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: LegacyServerListPingPacket) {
            output.writeUByte(value.payload)
        }
    }
}