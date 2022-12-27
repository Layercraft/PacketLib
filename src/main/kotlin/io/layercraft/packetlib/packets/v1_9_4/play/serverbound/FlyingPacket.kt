package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x0f | play | serverbound
 *
 * @property onGround onGround
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x0f, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class FlyingPacket(
    val onGround: Boolean,
) : ServerBoundPacket {

    companion object : PacketSerializer<FlyingPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): FlyingPacket {
            val onGround = input.readBoolean()

            return FlyingPacket(onGround)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: FlyingPacket) {
            output.writeBoolean(value.onGround)
        }
    }
}
