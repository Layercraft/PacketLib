package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.ClientBoundPacket
import io.layercraft.packetlib.packets.MinecraftPacket
import io.layercraft.packetlib.packets.PacketDirection
import io.layercraft.packetlib.packets.PacketSerializer
import io.layercraft.packetlib.packets.PacketState
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x6a | play | clientbound
 *

 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x6a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
class DeclareRecipesPacket() : ClientBoundPacket {

    companion object : PacketSerializer<DeclareRecipesPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): DeclareRecipesPacket {
            return DeclareRecipesPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: DeclareRecipesPacket) {
        }
    }
}