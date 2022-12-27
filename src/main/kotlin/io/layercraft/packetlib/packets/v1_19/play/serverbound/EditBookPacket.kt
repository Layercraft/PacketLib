package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x0d | play | serverbound
 *
 * @property hand hand
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x0d, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class EditBookPacket(
    val hand: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<EditBookPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EditBookPacket {
            val hand = input.readVarInt()

            return EditBookPacket(hand)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EditBookPacket) {
            output.writeVarInt(value.hand)
        }
    }
}