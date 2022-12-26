package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Select Advancements Tab | 0x41 | play | clientbound
 *

 * @see <a href="https://wiki.vg/Protocol#Select_Advancements_Tab">https://wiki.vg/Protocol#Select_Advancements_Tab</a>
 */

@MinecraftPacket(packetId = 0x41, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
class SelectAdvancementTabPacket() : ClientBoundPacket {

    companion object : PacketSerializer<SelectAdvancementTabPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SelectAdvancementTabPacket {
            return SelectAdvancementTabPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SelectAdvancementTabPacket) {
        }
    }
}