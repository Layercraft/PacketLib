package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Recipe Book | 0x39 | play | clientbound
 *
 * @param action action
 * @param craftingBookOpen craftingBookOpen
 * @param filteringCraftable filteringCraftable
 * @param smeltingBookOpen smeltingBookOpen
 * @param filteringSmeltable filteringSmeltable
 * @param blastFurnaceOpen blastFurnaceOpen
 * @param filteringBlastFurnace filteringBlastFurnace
 * @param smokerBookOpen smokerBookOpen
 * @param filteringSmoker filteringSmoker
 * @param recipes1 recipes1
 * @param recipes2 recipes2
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Update_Recipe_Book">https://wiki.vg/Protocol#Update_Recipe_Book</a>
 */

@MinecraftPacket(id = 0x39, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class UnlockRecipesPacket(
    val action: Int, // varint
    val craftingBookOpen: Boolean,
    val filteringCraftable: Boolean,
    val smeltingBookOpen: Boolean,
    val filteringSmeltable: Boolean,
    val blastFurnaceOpen: Boolean,
    val filteringBlastFurnace: Boolean,
    val smokerBookOpen: Boolean,
    val filteringSmoker: Boolean,
    val recipes1: List<String>, // varint array
    val recipes2: List<String>?,
) : ClientBoundPacket {
    companion object : PacketSerializer<UnlockRecipesPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): UnlockRecipesPacket {
            val action = input.readVarInt()
            val craftingBookOpen = input.readBoolean()
            val filteringCraftable = input.readBoolean()
            val smeltingBookOpen = input.readBoolean()
            val filteringSmeltable = input.readBoolean()
            val blastFurnaceOpen = input.readBoolean()
            val filteringBlastFurnace = input.readBoolean()
            val smokerBookOpen = input.readBoolean()
            val filteringSmoker = input.readBoolean()
            val recipes1 = input.readVarIntArray { arrayInput -> arrayInput.readString() }
            val recipes2 = when (action) {
                0 -> input.readVarIntArray { arrayInput -> arrayInput.readString() }
                else -> null
            }

            return UnlockRecipesPacket(action, craftingBookOpen, filteringCraftable, smeltingBookOpen, filteringSmeltable, blastFurnaceOpen, filteringBlastFurnace, smokerBookOpen, filteringSmoker, recipes1, recipes2)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: UnlockRecipesPacket) {
            output.writeVarInt(value.action)
            output.writeBoolean(value.craftingBookOpen)
            output.writeBoolean(value.filteringCraftable)
            output.writeBoolean(value.smeltingBookOpen)
            output.writeBoolean(value.filteringSmeltable)
            output.writeBoolean(value.blastFurnaceOpen)
            output.writeBoolean(value.filteringBlastFurnace)
            output.writeBoolean(value.smokerBookOpen)
            output.writeBoolean(value.filteringSmoker)
            output.writeVarIntArray(value.recipes1) { arrayValue, arrayOutput -> arrayOutput.writeString(arrayValue) }
            when (value.action) {
                0 -> output.writeVarIntArray(value.recipes2!!) { arrayValue, arrayOutput -> arrayOutput.writeString(arrayValue) }
                else -> {}
            }
        }
    }
}