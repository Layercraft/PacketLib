package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Seen Recipe | 0x22 | play | serverbound
 *
 * @param recipeId recipeId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Set_Seen_Recipe">https://wiki.vg/Protocol#Set_Seen_Recipe</a>
 */

@MinecraftPacket(id = 0x22, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class DisplayedRecipePacket(
    val recipeId: String,
) : ServerBoundPacket {
    companion object : PacketSerializer<DisplayedRecipePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): DisplayedRecipePacket {
            val recipeId = input.readString()

            return DisplayedRecipePacket(recipeId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: DisplayedRecipePacket) {
            output.writeString(value.recipeId)
        }
    }
}