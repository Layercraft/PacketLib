package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Change Recipe Book Settings | 0x21 | play | serverbound
 *
 * @param bookId bookId
 * @param bookOpen bookOpen
 * @param filterActive filterActive
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18071#Change_Recipe_Book_Settings">https://wiki.vg/Protocol#Change_Recipe_Book_Settings</a>
 */

@MinecraftPacket(id = 0x21, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class RecipeBookPacket(
    val bookId: Int, // varint
    val bookOpen: Boolean,
    val filterActive: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<RecipeBookPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): RecipeBookPacket {
            val bookId = input.readVarInt()
            val bookOpen = input.readBoolean()
            val filterActive = input.readBoolean()

            return RecipeBookPacket(bookId, bookOpen, filterActive)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: RecipeBookPacket) {
            output.writeVarInt(value.bookId)
            output.writeBoolean(value.bookOpen)
            output.writeBoolean(value.filterActive)
        }
    }
}