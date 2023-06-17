package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Seen Recipe | 0x22 | play | serverbound
 *
 * @param recipeId recipeId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Seen_Recipe">https://wiki.vg/Protocol#Set_Seen_Recipe</a>
 */

data class DisplayedRecipePacket(
    val recipeId: String,
) : ServerBoundPacket {
    companion object : PacketSerializer<DisplayedRecipePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): DisplayedRecipePacket {
            val recipeId = input.readString()

            return DisplayedRecipePacket(recipeId)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: DisplayedRecipePacket) {
            output.writeString(value.recipeId)
        }
    }
}