package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x2c | play | clientbound
 *
 * @property hand hand
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x2c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class OpenBookPacket(
    val hand: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<OpenBookPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): OpenBookPacket {
            val hand = input.readVarInt()

            return OpenBookPacket(hand)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: OpenBookPacket) {
            output.writeVarInt(value.hand)
        }
    }
}