package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x3e | play | clientbound
 *

 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x3e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
class SelectAdvancementTabPacket() : ClientBoundPacket {

    companion object : PacketSerializer<SelectAdvancementTabPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SelectAdvancementTabPacket {
            return SelectAdvancementTabPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SelectAdvancementTabPacket) {
        }
    }
}