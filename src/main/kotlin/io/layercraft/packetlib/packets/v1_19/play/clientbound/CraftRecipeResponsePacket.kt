package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x2e | play | clientbound
 *
 * @property windowId windowId
 * @property recipe recipe
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x2e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class CraftRecipeResponsePacket(
    val windowId: Byte,
    val recipe: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<CraftRecipeResponsePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): CraftRecipeResponsePacket {
            val windowId = input.readByte()
            val recipe = input.readString()

            return CraftRecipeResponsePacket(windowId, recipe)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: CraftRecipeResponsePacket) {
            output.writeByte(value.windowId)
            output.writeString(value.recipe)
        }
    }
}