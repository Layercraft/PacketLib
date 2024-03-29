package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Paddle Boat | 0x18 | play | serverbound
 *
 * @param leftPaddle leftPaddle
 * @param rightPaddle rightPaddle
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Paddle_Boat">https://wiki.vg/Protocol#Paddle_Boat</a>
 */

data class SteerBoatPacket(
    val leftPaddle: Boolean,
    val rightPaddle: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<SteerBoatPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SteerBoatPacket {
            val leftPaddle = input.readBoolean()
            val rightPaddle = input.readBoolean()

            return SteerBoatPacket(leftPaddle, rightPaddle)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SteerBoatPacket) {
            output.writeBoolean(value.leftPaddle)
            output.writeBoolean(value.rightPaddle)
        }
    }
}