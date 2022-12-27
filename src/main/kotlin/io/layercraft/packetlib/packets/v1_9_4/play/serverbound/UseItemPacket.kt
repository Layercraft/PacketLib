package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x1d | play | serverbound
 *
 * @property hand hand
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x1d, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class UseItemPacket(
    val hand: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<UseItemPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): UseItemPacket {
            val hand = input.readVarInt()

            return UseItemPacket(hand)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: UseItemPacket) {
            output.writeVarInt(value.hand)
        }
    }
}
