package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Recipe Book | 0x37 | play | clientbound
 *
 * @property action action
 * @property craftingBookOpen craftingBookOpen
 * @property filteringCraftable filteringCraftable
 * @property smeltingBookOpen smeltingBookOpen
 * @property filteringSmeltable filteringSmeltable
 * @property blastFurnaceOpen blastFurnaceOpen
 * @property filteringBlastFurnace filteringBlastFurnace
 * @property smokerBookOpen smokerBookOpen
 * @property filteringSmoker filteringSmoker
 * @see <a href="https://wiki.vg/Protocol#Update_Recipe_Book">https://wiki.vg/Protocol#Update_Recipe_Book</a>
 */

@MinecraftPacket(id = 0x37, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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
) : ClientBoundPacket {

    companion object : PacketSerializer<UnlockRecipesPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): UnlockRecipesPacket {
            val action = input.readVarInt()
            val craftingBookOpen = input.readBoolean()
            val filteringCraftable = input.readBoolean()
            val smeltingBookOpen = input.readBoolean()
            val filteringSmeltable = input.readBoolean()
            val blastFurnaceOpen = input.readBoolean()
            val filteringBlastFurnace = input.readBoolean()
            val smokerBookOpen = input.readBoolean()
            val filteringSmoker = input.readBoolean()

            return UnlockRecipesPacket(action, craftingBookOpen, filteringCraftable, smeltingBookOpen, filteringSmeltable, blastFurnaceOpen, filteringBlastFurnace, smokerBookOpen, filteringSmoker)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: UnlockRecipesPacket) {
            output.writeVarInt(value.action)
            output.writeBoolean(value.craftingBookOpen)
            output.writeBoolean(value.filteringCraftable)
            output.writeBoolean(value.smeltingBookOpen)
            output.writeBoolean(value.filteringSmeltable)
            output.writeBoolean(value.blastFurnaceOpen)
            output.writeBoolean(value.filteringBlastFurnace)
            output.writeBoolean(value.smokerBookOpen)
            output.writeBoolean(value.filteringSmoker)
        }
    }
}