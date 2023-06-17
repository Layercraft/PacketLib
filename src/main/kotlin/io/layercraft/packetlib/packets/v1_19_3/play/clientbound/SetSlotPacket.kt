package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Container Slot | 0x12 | play | clientbound
 *
 * @param windowId windowId
 * @param stateId stateId
 * @param slot slot
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Container_Slot">https://wiki.vg/Protocol#Set_Container_Slot</a>
 */

data class SetSlotPacket(
    val windowId: Byte,
    val stateId: Int, // varint
    val slot: Short,
) : ClientBoundPacket {
    companion object : PacketSerializer<SetSlotPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SetSlotPacket {
            val windowId = input.readByte()
            val stateId = input.readVarInt()
            val slot = input.readShort()

            return SetSlotPacket(windowId, stateId, slot)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SetSlotPacket) {
            output.writeByte(value.windowId)
            output.writeVarInt(value.stateId)
            output.writeShort(value.slot)
        }
    }
}