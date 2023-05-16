package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Place Ghost Recipe | 0x2f | play | clientbound
 *
 * @param windowId windowId
 * @param recipe recipe
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18071#Place_Ghost_Recipe">https://wiki.vg/Protocol#Place_Ghost_Recipe</a>
 */

@MinecraftPacket(id = 0x2f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class CraftRecipeResponsePacket(
    val windowId: Byte,
    val recipe: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<CraftRecipeResponsePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): CraftRecipeResponsePacket {
            val windowId = input.readByte()
            val recipe = input.readString()

            return CraftRecipeResponsePacket(windowId, recipe)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: CraftRecipeResponsePacket) {
            output.writeByte(value.windowId)
            output.writeString(value.recipe)
        }
    }
}