package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Container Content | 0x10 | play | clientbound
 *
 * @param windowId windowId
 * @param stateId stateId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Container_Content">https://wiki.vg/Protocol#Set_Container_Content</a>
 */

data class WindowItemsPacket(
    val windowId: UByte,
    val stateId: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<WindowItemsPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): WindowItemsPacket {
            val windowId = input.readUByte()
            val stateId = input.readVarInt()

            return WindowItemsPacket(windowId, stateId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: WindowItemsPacket) {
            output.writeUByte(value.windowId)
            output.writeVarInt(value.stateId)
        }
    }
}