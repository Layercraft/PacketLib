package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Recipes | 0x6a | play | clientbound
 *
 * @param recipes list of DeclareRecipesPacketRecipes
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
                val group = when (type) {
                    "minecraft:crafting_shapeless" -> arrayInput.readString()
                    "minecraft:crafting_shaped" -> arrayInput.readString()
                    "minecraft:stonecutting" -> arrayInput.readString()
                    else -> null
                }
                val width = when (type) {
                    "minecraft:crafting_shaped" -> arrayInput.readVarInt()
                    else -> null
                }
                val height = when (type) {
                    "minecraft:crafting_shaped" -> arrayInput.readVarInt()
                    else -> null
                }

                return@readVarIntArray DeclareRecipesPacketRecipes(type, recipeId, group, width, height)
            }

            return DeclareRecipesPacket(recipes)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: DeclareRecipesPacket) {
            output.writeVarIntArray(value.recipes) { arrayValue, arrayOutput ->
                arrayOutput.writeString(arrayValue.type)
                arrayOutput.writeString(arrayValue.recipeId)
                when (arrayValue.type) {
                    "minecraft:crafting_shapeless" -> arrayOutput.writeString(arrayValue.group!!)
                    "minecraft:crafting_shaped" -> arrayOutput.writeString(arrayValue.group!!)
                    "minecraft:stonecutting" -> arrayOutput.writeString(arrayValue.group!!)
                    else -> {}
                }
                when (arrayValue.type) {
                    "minecraft:crafting_shaped" -> arrayOutput.writeVarInt(arrayValue.width!!)
                    else -> {}
                }
                when (arrayValue.type) {
                    "minecraft:crafting_shaped" -> arrayOutput.writeVarInt(arrayValue.height!!)
                    else -> {}
                }
            }
        }
    }
}

/**
 * DeclareRecipesPacketRecipes
 *
 * @param type type
 * @param recipeId recipeId
 * @param group group
 * @param width width
 * @param height height
*/
data class DeclareRecipesPacketRecipes(
    val type: String,
    val recipeId: String,
    val group: String?,
    val width: Int?, // varint
    val height: Int?, // varint
)