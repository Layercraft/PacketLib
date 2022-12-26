package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x3f | play | clientbound
 *
 * @property previewsChat previewsChat
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x3f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ServerDataPacket(
    val previewsChat: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<ServerDataPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ServerDataPacket {
            val previewsChat = input.readBoolean()

            return ServerDataPacket(previewsChat)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ServerDataPacket) {
            output.writeBoolean(value.previewsChat)
        }
    }
}