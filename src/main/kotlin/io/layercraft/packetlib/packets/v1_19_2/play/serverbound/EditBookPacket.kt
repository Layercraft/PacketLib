package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x0e | play | serverbound
 *
 * @property hand hand
 * @property hasTitle title is present
 * @property title title
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x0e, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class EditBookPacket(
    val hand: Int, // varint
    val hasTitle: Boolean,
    val title: String?,
) : ServerBoundPacket {

    companion object : PacketSerializer<EditBookPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EditBookPacket {
            val hand = input.readVarInt()
            val hasTitle = input.readBoolean()
            val title = if (hasTitle) input.readString() else null

            return EditBookPacket(hand, hasTitle, title)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EditBookPacket) {
            output.writeVarInt(value.hand)
            output.writeBoolean(value.hasTitle)
            if (value.hasTitle) output.writeString(value.title!!)
        }
    }
}