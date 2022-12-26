package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x2e | play | serverbound
 *
 * @property hand hand
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x2e, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
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