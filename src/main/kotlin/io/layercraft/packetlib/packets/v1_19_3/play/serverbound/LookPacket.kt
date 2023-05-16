package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Player Rotation | 0x15 | play | serverbound
 *
 * @param yaw yaw
 * @param pitch pitch
 * @param onGround onGround
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18071#Set_Player_Rotation">https://wiki.vg/Protocol#Set_Player_Rotation</a>
 */

@MinecraftPacket(id = 0x15, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class LookPacket(
    val yaw: Float,
    val pitch: Float,
    val onGround: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<LookPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): LookPacket {
            val yaw = input.readFloat()
            val pitch = input.readFloat()
            val onGround = input.readBoolean()

            return LookPacket(yaw, pitch, onGround)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: LookPacket) {
            output.writeFloat(value.yaw)
            output.writeFloat(value.pitch)
            output.writeBoolean(value.onGround)
        }
    }
}