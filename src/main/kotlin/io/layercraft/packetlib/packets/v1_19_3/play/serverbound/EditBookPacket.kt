package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Edit Book | 0x0d | play | serverbound
 *
 * @param hand hand
 * @param pages pages
 * @param hasTitle title is present
 * @param title title
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Edit_Book">https://wiki.vg/Protocol#Edit_Book</a>
 */

data class EditBookPacket(
    val hand: Int, // varint
    val pages: List<String>, // varint array
    val hasTitle: Boolean,
    val title: String?,
) : ServerBoundPacket {
    companion object : PacketSerializer<EditBookPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): EditBookPacket {
            val hand = input.readVarInt()
            val pages = input.readVarIntArray { arrayInput -> arrayInput.readString() }
            val hasTitle = input.readBoolean()
            val title = if (hasTitle) input.readString() else null

            return EditBookPacket(hand, pages, hasTitle, title)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: EditBookPacket) {
            output.writeVarInt(value.hand)
            output.writeVarIntArray(value.pages) { arrayValue, arrayOutput -> arrayOutput.writeString(arrayValue) }
            output.writeBoolean(value.hasTitle)
            if (value.hasTitle) output.writeString(value.title!!)
        }
    }
}