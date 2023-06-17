package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Open Horse Screen | 0x1d | play | clientbound
 *
 * @param windowId windowId
 * @param nbSlots nbSlots
 * @param entityId entityId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Open_Horse_Screen">https://wiki.vg/Protocol#Open_Horse_Screen</a>
 */

data class OpenHorseWindowPacket(
    val windowId: UByte,
    val nbSlots: Int, // varint
    val entityId: Int,
) : ClientBoundPacket {
    companion object : PacketSerializer<OpenHorseWindowPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): OpenHorseWindowPacket {
            val windowId = input.readUByte()
            val nbSlots = input.readVarInt()
            val entityId = input.readInt()

            return OpenHorseWindowPacket(windowId, nbSlots, entityId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: OpenHorseWindowPacket) {
            output.writeUByte(value.windowId)
            output.writeVarInt(value.nbSlots)
            output.writeInt(value.entityId)
        }
    }
}