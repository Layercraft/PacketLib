package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Seen Advancements | 0x25 | play | serverbound
 *
 * @param action action
 * @param tabId tabId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Seen_Advancements">https://wiki.vg/Protocol#Seen_Advancements</a>
 */

data class AdvancementTabPacket(
    val action: Int, // varint
    val tabId: String?,
) : ServerBoundPacket {
    companion object : PacketSerializer<AdvancementTabPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): AdvancementTabPacket {
            val action = input.readVarInt()
            val tabId = when (action) {
                0 -> input.readString()
                1 -> null
                else -> null
            }

            return AdvancementTabPacket(action, tabId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: AdvancementTabPacket) {
            output.writeVarInt(value.action)
            when (value.action) {
                0 -> output.writeString(value.tabId!!)
                else -> {}
            }
        }
    }
}