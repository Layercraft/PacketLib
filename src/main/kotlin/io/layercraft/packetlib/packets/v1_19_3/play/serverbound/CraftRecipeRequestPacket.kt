package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Place Recipe | 0x1a | play | serverbound
 *
 * @param windowId windowId
 * @param recipe recipe
 * @param makeAll makeAll
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Place_Recipe">https://wiki.vg/Protocol#Place_Recipe</a>
 */

data class CraftRecipeRequestPacket(
    val windowId: Byte,
    val recipe: String,
    val makeAll: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<CraftRecipeRequestPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): CraftRecipeRequestPacket {
            val windowId = input.readByte()
            val recipe = input.readString()
            val makeAll = input.readBoolean()

            return CraftRecipeRequestPacket(windowId, recipe, makeAll)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: CraftRecipeRequestPacket) {
            output.writeByte(value.windowId)
            output.writeString(value.recipe)
            output.writeBoolean(value.makeAll)
        }
    }
}