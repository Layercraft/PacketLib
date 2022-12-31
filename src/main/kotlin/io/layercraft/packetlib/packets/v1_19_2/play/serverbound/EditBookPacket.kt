package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Edit Book | 0x0e | play | serverbound
 *
 * @property hand hand
 * @property pages pages
 * @property hasTitle title is present
 * @property title title
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Edit_Book">https://wiki.vg/Protocol#Edit_Book</a>
 */

@MinecraftPacket(id = 0x0e, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class EditBookPacket(
    val hand: Int, // varint
    val pages: List<String>, // varint array
    val hasTitle: Boolean,
    val title: String?,
) : ServerBoundPacket {
    companion object : PacketSerializer<EditBookPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EditBookPacket {
            val hand = input.readVarInt()
            val pages = input.readVarIntArray { arrayInput -> arrayInput.readString() }
            val hasTitle = input.readBoolean()
            val title = if (hasTitle) input.readString() else null

            return EditBookPacket(hand, pages, hasTitle, title)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EditBookPacket) {
            output.writeVarInt(value.hand)
            output.writeVarIntArray(value.pages) { arrayValue, arrayOutput -> arrayOutput.writeString(arrayValue) }
            output.writeBoolean(value.hasTitle)
            if (value.hasTitle) output.writeString(value.title!!)
        }
    }
}
