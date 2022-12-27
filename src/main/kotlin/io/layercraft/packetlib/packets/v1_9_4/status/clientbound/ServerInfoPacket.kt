package io.layercraft.packetlib.packets.v1_9_4.status.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Response | 0x00 | status | clientbound
 *
 * @property response response
 * @see <a href="https://wiki.vg/Protocol#Response">https://wiki.vg/Protocol#Response</a>
 */

@MinecraftPacket(packetId = 0x00, state = PacketState.STATUS, direction = PacketDirection.CLIENTBOUND)
data class ServerInfoPacket(
    val response: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<ServerInfoPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ServerInfoPacket {
            val response = input.readString()

            return ServerInfoPacket(response)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ServerInfoPacket) {
            output.writeString(value.response)
        }
    }
}
