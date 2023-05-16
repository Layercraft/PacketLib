package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Disconnect (play) | 0x17 | play | clientbound
 *
 * @param reason reason
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18071#Disconnect_.28play.29">https://wiki.vg/Protocol#Disconnect_.28play.29</a>
 */

@MinecraftPacket(id = 0x17, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class KickDisconnectPacket(
    val reason: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<KickDisconnectPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): KickDisconnectPacket {
            val reason = input.readString()

            return KickDisconnectPacket(reason)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: KickDisconnectPacket) {
            output.writeString(value.reason)
        }
    }
}