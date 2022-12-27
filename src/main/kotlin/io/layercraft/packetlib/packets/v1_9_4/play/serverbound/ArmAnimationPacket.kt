package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x1a | play | serverbound
 *
 * @property hand hand
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x1a, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class ArmAnimationPacket(
    val hand: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<ArmAnimationPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ArmAnimationPacket {
            val hand = input.readVarInt()

            return ArmAnimationPacket(hand)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ArmAnimationPacket) {
            output.writeVarInt(value.hand)
        }
    }
}
