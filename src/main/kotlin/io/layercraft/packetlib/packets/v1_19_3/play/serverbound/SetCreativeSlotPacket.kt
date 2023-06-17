package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Creative Mode Slot | 0x2b | play | serverbound
 *
 * @param slot slot
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Creative_Mode_Slot">https://wiki.vg/Protocol#Set_Creative_Mode_Slot</a>
 */

data class SetCreativeSlotPacket(
    val slot: Short,
) : ServerBoundPacket {
    companion object : PacketSerializer<SetCreativeSlotPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SetCreativeSlotPacket {
            val slot = input.readShort()

            return SetCreativeSlotPacket(slot)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SetCreativeSlotPacket) {
            output.writeShort(value.slot)
        }
    }
}