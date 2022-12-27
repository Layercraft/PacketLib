package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x1a | play | serverbound
 *
 * @property windowId windowId
 * @property recipe recipe
 * @property makeAll makeAll
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x1a, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class CraftRecipeRequestPacket(
    val windowId: Byte,
    val recipe: String,
    val makeAll: Boolean,
) : ServerBoundPacket {

    companion object : PacketSerializer<CraftRecipeRequestPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): CraftRecipeRequestPacket {
            val windowId = input.readByte()
            val recipe = input.readString()
            val makeAll = input.readBoolean()

            return CraftRecipeRequestPacket(windowId, recipe, makeAll)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: CraftRecipeRequestPacket) {
            output.writeByte(value.windowId)
            output.writeString(value.recipe)
            output.writeBoolean(value.makeAll)
        }
    }
}