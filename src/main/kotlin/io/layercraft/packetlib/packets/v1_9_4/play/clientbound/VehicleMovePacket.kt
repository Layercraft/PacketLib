package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 *  | 0x29 | play | clientbound
 *
 * @property x x
 * @property y y
 * @property z z
 * @property yaw yaw
 * @property pitch pitch
 * @see <a href="https://wiki.vg/Protocol#Vehicle_Move_.28clientbound.29">https://wiki.vg/Protocol#Vehicle_Move_.28clientbound.29</a>
 */

@MinecraftPacket(packetId = 0x29, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class VehicleMovePacket(
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float,
) : ClientBoundPacket {

    companion object : PacketSerializer<VehicleMovePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): VehicleMovePacket {
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val yaw = input.readFloat()
            val pitch = input.readFloat()

            return VehicleMovePacket(x, y, z, yaw, pitch)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: VehicleMovePacket) {
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeFloat(value.yaw)
            output.writeFloat(value.pitch)
        }
    }
}
