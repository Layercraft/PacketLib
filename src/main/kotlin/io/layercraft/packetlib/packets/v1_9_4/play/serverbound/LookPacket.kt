package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x0e | play | serverbound
 *
 * @property yaw yaw
 * @property pitch pitch
 * @property onGround onGround
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x0e, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class LookPacket(
    val yaw: Float,
    val pitch: Float,
    val onGround: Boolean,
) : ServerBoundPacket {

    companion object : PacketSerializer<LookPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): LookPacket {
            val yaw = input.readFloat()
            val pitch = input.readFloat()
            val onGround = input.readBoolean()

            return LookPacket(yaw, pitch, onGround)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: LookPacket) {
            output.writeFloat(value.yaw)
            output.writeFloat(value.pitch)
            output.writeBoolean(value.onGround)
        }
    }
}
