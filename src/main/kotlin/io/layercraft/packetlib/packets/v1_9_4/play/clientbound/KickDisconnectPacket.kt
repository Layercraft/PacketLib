package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x1a | play | clientbound
 *
 * @property reason reason
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x1a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class KickDisconnectPacket(
    val reason: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<KickDisconnectPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): KickDisconnectPacket {
            val reason = input.readString()

            return KickDisconnectPacket(reason)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: KickDisconnectPacket) {
            output.writeString(value.reason)
        }
    }
}
