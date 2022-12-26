package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Server Data | 0x42 | play | clientbound
 *
 * @property previewsChat previewsChat
 * @property enforcesSecureChat enforcesSecureChat
 * @see <a href="https://wiki.vg/Protocol#Server_Data">https://wiki.vg/Protocol#Server_Data</a>
 */

@MinecraftPacket(packetId = 0x42, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ServerDataPacket(
    val previewsChat: Boolean,
    val enforcesSecureChat: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<ServerDataPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ServerDataPacket {
            val previewsChat = input.readBoolean()
            val enforcesSecureChat = input.readBoolean()

            return ServerDataPacket(previewsChat, enforcesSecureChat)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ServerDataPacket) {
            output.writeBoolean(value.previewsChat)
            output.writeBoolean(value.enforcesSecureChat)
        }
    }
}