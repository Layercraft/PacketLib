package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Container Property | 0x11 | play | clientbound
 *
 * @param windowId windowId
 * @param property property
 * @param value value
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Container_Property">https://wiki.vg/Protocol#Set_Container_Property</a>
 */

data class CraftProgressBarPacket(
    val windowId: UByte,
    val property: Short,
    val value: Short,
) : ClientBoundPacket {
    companion object : PacketSerializer<CraftProgressBarPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): CraftProgressBarPacket {
            val windowId = input.readUByte()
            val property = input.readShort()
            val value = input.readShort()

            return CraftProgressBarPacket(windowId, property, value)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: CraftProgressBarPacket) {
            output.writeUByte(value.windowId)
            output.writeShort(value.property)
            output.writeShort(value.value)
        }
    }
}