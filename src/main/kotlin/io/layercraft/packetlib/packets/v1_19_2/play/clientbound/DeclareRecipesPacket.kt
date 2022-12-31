package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Recipes | 0x6a | play | clientbound
 *
 * @property recipes recipes
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Update_Recipes">https://wiki.vg/Protocol#Update_Recipes</a>
 */

@MinecraftPacket(id = 0x6a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class DeclareRecipesPacket(
    val recipes: List<DeclareRecipesPacketRecipes>, // varint array
) : ClientBoundPacket {
    companion object : PacketSerializer<DeclareRecipesPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): DeclareRecipesPacket {
            val recipes = input.readVarIntArray { arrayInput -> 
                val type = arrayInput.readString()
                val recipeId = arrayInput.readString()

                return@readVarIntArray DeclareRecipesPacketRecipes(type, recipeId)
            }

            return DeclareRecipesPacket(recipes)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: DeclareRecipesPacket) {
            output.writeVarIntArray(value.recipes) { arrayValue, arrayOutput -> 
                arrayOutput.writeString(arrayValue.type)
                arrayOutput.writeString(arrayValue.recipeId)
            }
        }
    }
}

/**
 * DeclareRecipesPacketRecipes | recipes
 *
 * @property type type
 * @property recipeId recipeId
*/
data class DeclareRecipesPacketRecipes(
    val type: String,
    val recipeId: String,
)
