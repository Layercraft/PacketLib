package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Change Recipe Book Settings | 0x21 | play | serverbound
 *
 * @param bookId bookId
 * @param bookOpen bookOpen
 * @param filterActive filterActive
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Change_Recipe_Book_Settings">https://wiki.vg/Protocol#Change_Recipe_Book_Settings</a>
 */

data class RecipeBookPacket(
    val bookId: Int, // varint
    val bookOpen: Boolean,
    val filterActive: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<RecipeBookPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): RecipeBookPacket {
            val bookId = input.readVarInt()
            val bookOpen = input.readBoolean()
            val filterActive = input.readBoolean()

            return RecipeBookPacket(bookId, bookOpen, filterActive)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: RecipeBookPacket) {
            output.writeVarInt(value.bookId)
            output.writeBoolean(value.bookOpen)
            output.writeBoolean(value.filterActive)
        }
    }
}