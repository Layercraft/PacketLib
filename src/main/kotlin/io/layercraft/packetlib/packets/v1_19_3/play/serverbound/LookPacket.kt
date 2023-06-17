package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Set Player Rotation | 0x15 | play | serverbound
 *
 * @param yaw yaw
 * @param pitch pitch
 * @param onGround onGround
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Player_Rotation">https://wiki.vg/Protocol#Set_Player_Rotation</a>
 */

data class LookPacket(
    val yaw: Float,
    val pitch: Float,
    val onGround: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<LookPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): LookPacket {
            val yaw = input.readFloat()
            val pitch = input.readFloat()
            val onGround = input.readBoolean()

            return LookPacket(yaw, pitch, onGround)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: LookPacket) {
            output.writeFloat(value.yaw)
            output.writeFloat(value.pitch)
            output.writeBoolean(value.onGround)
        }
    }
}