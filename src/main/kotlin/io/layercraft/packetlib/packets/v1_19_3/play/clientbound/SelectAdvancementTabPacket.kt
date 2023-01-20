package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Select Advancements Tab | 0x40 | play | clientbound
 *
 * @param hasId id is present
 * @param id id
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17935#Select_Advancements_Tab">https://wiki.vg/Protocol#Select_Advancements_Tab</a>
 */

@MinecraftPacket(id = 0x40, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SelectAdvancementTabPacket(
    val hasId: Boolean,
    val id: String?,
) : ClientBoundPacket {
    companion object : PacketSerializer<SelectAdvancementTabPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SelectAdvancementTabPacket {
            val hasId = input.readBoolean()
            val id = if (hasId) input.readString() else null

            return SelectAdvancementTabPacket(hasId, id)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SelectAdvancementTabPacket) {
            output.writeBoolean(value.hasId)
            if (value.hasId) output.writeString(value.id!!)
        }
    }
}