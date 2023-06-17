package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Swing Arm | 0x2f | play | serverbound
 *
 * @param hand hand
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Swing_Arm">https://wiki.vg/Protocol#Swing_Arm</a>
 */

data class ArmAnimationPacket(
    val hand: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<ArmAnimationPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): ArmAnimationPacket {
            val hand = input.readVarInt()

            return ArmAnimationPacket(hand)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: ArmAnimationPacket) {
            output.writeVarInt(value.hand)
        }
    }
}