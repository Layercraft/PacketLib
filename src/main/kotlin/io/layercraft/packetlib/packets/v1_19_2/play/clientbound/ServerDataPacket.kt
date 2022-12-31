package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Server Data | 0x42 | play | clientbound
 *
 * @property hasMotd motd is present
 * @property motd motd
 * @property hasIcon icon is present
 * @property icon icon
 * @property previewsChat previewsChat
 * @property enforcesSecureChat enforcesSecureChat
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Server_Data">https://wiki.vg/Protocol#Server_Data</a>
 */

@MinecraftPacket(id = 0x42, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ServerDataPacket(
    val hasMotd: Boolean,
    val motd: String?,
    val hasIcon: Boolean,
    val icon: String?,
    val previewsChat: Boolean,
    val enforcesSecureChat: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<ServerDataPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ServerDataPacket {
            val hasMotd = input.readBoolean()
            val motd = if (hasMotd) input.readString() else null
            val hasIcon = input.readBoolean()
            val icon = if (hasIcon) input.readString() else null
            val previewsChat = input.readBoolean()
            val enforcesSecureChat = input.readBoolean()

            return ServerDataPacket(hasMotd, motd, hasIcon, icon, previewsChat, enforcesSecureChat)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ServerDataPacket) {
            output.writeBoolean(value.hasMotd)
            if (value.hasMotd) output.writeString(value.motd!!)
            output.writeBoolean(value.hasIcon)
            if (value.hasIcon) output.writeString(value.icon!!)
            output.writeBoolean(value.previewsChat)
            output.writeBoolean(value.enforcesSecureChat)
        }
    }
}
