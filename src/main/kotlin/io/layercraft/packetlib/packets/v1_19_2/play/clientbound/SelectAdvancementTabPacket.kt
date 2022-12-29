package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Select Advancements Tab | 0x41 | play | clientbound
 *
 * @property hasId id is present
 * @property id id
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Select_Advancements_Tab">https://wiki.vg/Protocol#Select_Advancements_Tab</a>
 */

@MinecraftPacket(id = 0x41, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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