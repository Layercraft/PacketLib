package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x6a | play | clientbound
 *

 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x6a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
class DeclareRecipesPacket() : ClientBoundPacket {
    companion object : PacketSerializer<DeclareRecipesPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): DeclareRecipesPacket {
            return DeclareRecipesPacket()
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: DeclareRecipesPacket) {
        }
    }
}