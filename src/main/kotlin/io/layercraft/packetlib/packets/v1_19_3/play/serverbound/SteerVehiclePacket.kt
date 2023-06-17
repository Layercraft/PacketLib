package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Player Input | 0x1e | play | serverbound
 *
 * @param sideways sideways
 * @param forward forward
 * @param jump jump
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Player_Input">https://wiki.vg/Protocol#Player_Input</a>
 */

data class SteerVehiclePacket(
    val sideways: Float,
    val forward: Float,
    val jump: UByte,
) : ServerBoundPacket {
    companion object : PacketSerializer<SteerVehiclePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): SteerVehiclePacket {
            val sideways = input.readFloat()
            val forward = input.readFloat()
            val jump = input.readUByte()

            return SteerVehiclePacket(sideways, forward, jump)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: SteerVehiclePacket) {
            output.writeFloat(value.sideways)
            output.writeFloat(value.forward)
            output.writeUByte(value.jump)
        }
    }
}