package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Pick Item | 0x19 | play | serverbound
 *
 * @param slot slot
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Pick_Item">https://wiki.vg/Protocol#Pick_Item</a>
 */

data class PickItemPacket(
    val slot: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<PickItemPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): PickItemPacket {
            val slot = input.readVarInt()

            return PickItemPacket(slot)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: PickItemPacket) {
            output.writeVarInt(value.slot)
        }
    }
}