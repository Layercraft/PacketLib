package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Player Rotation | 0x16 | play | serverbound
 *
 * @property yaw yaw
 * @property pitch pitch
 * @property onGround onGround
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Set_Player_Rotation">https://wiki.vg/Protocol#Set_Player_Rotation</a>
 */

@MinecraftPacket(id = 0x16, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
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