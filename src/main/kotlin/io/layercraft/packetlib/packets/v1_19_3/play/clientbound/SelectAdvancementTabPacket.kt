package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Select Advancements Tab | 0x40 | play | clientbound
 *
 * @param hasId id is present
 * @param id id
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Select_Advancements_Tab">https://wiki.vg/Protocol#Select_Advancements_Tab</a>
 */

data class SelectAdvancementTabPacket(
    val hasId: Boolean,
    val id: String?,
) : ClientBoundPacket {
    companion object : PacketSerializer<SelectAdvancementTabPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SelectAdvancementTabPacket {
            val hasId = input.readBoolean()
            val id = if (hasId) input.readString() else null

            return SelectAdvancementTabPacket(hasId, id)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SelectAdvancementTabPacket) {
            output.writeBoolean(value.hasId)
            if (value.hasId) output.writeString(value.id!!)
        }
    }
}