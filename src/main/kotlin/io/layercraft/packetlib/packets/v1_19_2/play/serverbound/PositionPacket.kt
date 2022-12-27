package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Player Position | 0x14 | play | serverbound
 *
 * @property x x
 * @property y y
 * @property z z
 * @property onGround onGround
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Set_Player_Position">https://wiki.vg/Protocol#Set_Player_Position</a>
 */

@MinecraftPacket(id = 0x14, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class PositionPacket(
    val x: Double,
    val y: Double,
    val z: Double,
    val onGround: Boolean,
) : ServerBoundPacket {

    companion object : PacketSerializer<PositionPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): PositionPacket {
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val onGround = input.readBoolean()

            return PositionPacket(x, y, z, onGround)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PositionPacket) {
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeBoolean(value.onGround)
        }
    }
}