package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Held Item | 0x28 | play | serverbound
 *
 * @param slotId slotId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Held_Item_2">https://wiki.vg/Protocol#Set_Held_Item_2</a>
 */

data class HeldItemSlotPacket(
    val slotId: Short,
) : ServerBoundPacket {
    companion object : PacketSerializer<HeldItemSlotPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): HeldItemSlotPacket {
            val slotId = input.readShort()

            return HeldItemSlotPacket(slotId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: HeldItemSlotPacket) {
            output.writeShort(value.slotId)
        }
    }
}