package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Player Input | 0x1e | play | serverbound
 *
 * @param sideways sideways
 * @param forward forward
 * @param jump jump
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18071#Player_Input">https://wiki.vg/Protocol#Player_Input</a>
 */

@MinecraftPacket(id = 0x1e, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class SteerVehiclePacket(
    val sideways: Float,
    val forward: Float,
    val jump: UByte,
) : ServerBoundPacket {
    companion object : PacketSerializer<SteerVehiclePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SteerVehiclePacket {
            val sideways = input.readFloat()
            val forward = input.readFloat()
            val jump = input.readUByte()

            return SteerVehiclePacket(sideways, forward, jump)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SteerVehiclePacket) {
            output.writeFloat(value.sideways)
            output.writeFloat(value.forward)
            output.writeUByte(value.jump)
        }
    }
}