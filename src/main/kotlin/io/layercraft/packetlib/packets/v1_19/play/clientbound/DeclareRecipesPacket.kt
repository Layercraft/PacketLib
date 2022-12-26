package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Recipes | 0x67 | play | clientbound
 *

 * @see <a href="https://wiki.vg/Protocol#Update_Recipes">https://wiki.vg/Protocol#Update_Recipes</a>
 */

@MinecraftPacket(packetId = 0x67, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
class DeclareRecipesPacket() : ClientBoundPacket {

    companion object : PacketSerializer<DeclareRecipesPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): DeclareRecipesPacket {
            return DeclareRecipesPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: DeclareRecipesPacket) {
        }
    }
}