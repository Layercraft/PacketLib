package io.layercraft.packetlib.packets.v1_19_3.login.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Disconnect (login) | 0x00 | login | clientbound
 *
 * @param reason reason
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Disconnect_.28login.29">https://wiki.vg/Protocol#Disconnect_.28login.29</a>
 */

data class DisconnectPacket(
    val reason: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<DisconnectPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): DisconnectPacket {
            val reason = input.readString()

            return DisconnectPacket(reason)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: DisconnectPacket) {
            output.writeString(value.reason)
        }
    }
}