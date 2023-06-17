package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Held Item | 0x49 | play | clientbound
 *
 * @param slot slot
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Held_Item">https://wiki.vg/Protocol#Set_Held_Item</a>
 */

data class HeldItemSlotPacket(
    val slot: Byte,
) : ClientBoundPacket {
    companion object : PacketSerializer<HeldItemSlotPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): HeldItemSlotPacket {
            val slot = input.readByte()

            return HeldItemSlotPacket(slot)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: HeldItemSlotPacket) {
            output.writeByte(value.slot)
        }
    }
}