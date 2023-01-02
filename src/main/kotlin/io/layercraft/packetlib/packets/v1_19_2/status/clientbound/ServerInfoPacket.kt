package io.layercraft.packetlib.packets.v1_19_2.status.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Status Response | 0x00 | status | clientbound
 *
 * @param response response
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Status_Response">https://wiki.vg/Protocol#Status_Response</a>
 */

@MinecraftPacket(id = 0x00, state = PacketState.STATUS, direction = PacketDirection.CLIENTBOUND)
data class ServerInfoPacket(
    val response: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<ServerInfoPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ServerInfoPacket {
            val response = input.readString()

            return ServerInfoPacket(response)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ServerInfoPacket) {
            output.writeString(value.response)
        }
    }
}