package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Swing Arm | 0x2f | play | serverbound
 *
 * @param hand hand
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Swing_Arm">https://wiki.vg/Protocol#Swing_Arm</a>
 */

@MinecraftPacket(id = 0x2f, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class ArmAnimationPacket(
    val hand: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<ArmAnimationPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ArmAnimationPacket {
            val hand = input.readVarInt()

            return ArmAnimationPacket(hand)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ArmAnimationPacket) {
            output.writeVarInt(value.hand)
        }
    }
}