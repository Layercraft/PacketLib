package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x1f | play | serverbound
 *
 * @property sideways sideways
 * @property forward forward
 * @property jump jump
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x1f, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class SteerVehiclePacket(
    val sideways: Float,
    val forward: Float,
    val jump: UByte,
) : ServerBoundPacket {

    companion object : PacketSerializer<SteerVehiclePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SteerVehiclePacket {
            val sideways = input.readFloat()
            val forward = input.readFloat()
            val jump = input.readUByte()

            return SteerVehiclePacket(sideways, forward, jump)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SteerVehiclePacket) {
            output.writeFloat(value.sideways)
            output.writeFloat(value.forward)
            output.writeUByte(value.jump)
        }
    }
}