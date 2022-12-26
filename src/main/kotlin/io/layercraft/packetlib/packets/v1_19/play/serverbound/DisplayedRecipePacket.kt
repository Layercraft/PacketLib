package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Seen Recipe | 0x21 | play | serverbound
 *
 * @property recipeId recipeId
 * @see <a href="https://wiki.vg/Protocol#Set_Seen_Recipe">https://wiki.vg/Protocol#Set_Seen_Recipe</a>
 */

@MinecraftPacket(packetId = 0x21, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class DisplayedRecipePacket(
    val recipeId: String,
) : ServerBoundPacket {

    companion object : PacketSerializer<DisplayedRecipePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): DisplayedRecipePacket {
            val recipeId = input.readString()

            return DisplayedRecipePacket(recipeId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: DisplayedRecipePacket) {
            output.writeString(value.recipeId)
        }
    }
}