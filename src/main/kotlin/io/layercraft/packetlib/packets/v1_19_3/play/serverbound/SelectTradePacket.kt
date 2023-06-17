package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Select Trade | 0x26 | play | serverbound
 *
 * @param slot slot
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Select_Trade">https://wiki.vg/Protocol#Select_Trade</a>
 */

data class SelectTradePacket(
    val slot: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<SelectTradePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SelectTradePacket {
            val slot = input.readVarInt()

            return SelectTradePacket(slot)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SelectTradePacket) {
            output.writeVarInt(value.slot)
        }
    }
}