package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x32 | play | serverbound
 *
 * @param hand hand
 * @param sequence sequence
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18071#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x32, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class UseItemPacket(
    val hand: Int, // varint
    val sequence: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<UseItemPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): UseItemPacket {
            val hand = input.readVarInt()
            val sequence = input.readVarInt()

            return UseItemPacket(hand, sequence)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: UseItemPacket) {
            output.writeVarInt(value.hand)
            output.writeVarInt(value.sequence)
        }
    }
}