package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Close Container | 0x0f | play | clientbound
 *
 * @param windowId windowId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Close_Container">https://wiki.vg/Protocol#Close_Container</a>
 */

data class CloseWindowPacket(
    val windowId: UByte,
) : ClientBoundPacket {
    companion object : PacketSerializer<CloseWindowPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): CloseWindowPacket {
            val windowId = input.readUByte()

            return CloseWindowPacket(windowId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: CloseWindowPacket) {
            output.writeUByte(value.windowId)
        }
    }
}