package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Server Data | 0x41 | play | clientbound
 *
 * @param hasMotd motd is present
 * @param motd motd
 * @param hasIcon icon is present
 * @param icon icon
 * @param enforcesSecureChat enforcesSecureChat
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Server_Data">https://wiki.vg/Protocol#Server_Data</a>
 */

data class ServerDataPacket(
    val hasMotd: Boolean,
    val motd: String?,
    val hasIcon: Boolean,
    val icon: String?,
    val enforcesSecureChat: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<ServerDataPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): ServerDataPacket {
            val hasMotd = input.readBoolean()
            val motd = if (hasMotd) input.readString() else null
            val hasIcon = input.readBoolean()
            val icon = if (hasIcon) input.readString() else null
            val enforcesSecureChat = input.readBoolean()

            return ServerDataPacket(hasMotd, motd, hasIcon, icon, enforcesSecureChat)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: ServerDataPacket) {
            output.writeBoolean(value.hasMotd)
            if (value.hasMotd) output.writeString(value.motd!!)
            output.writeBoolean(value.hasIcon)
            if (value.hasIcon) output.writeString(value.icon!!)
            output.writeBoolean(value.enforcesSecureChat)
        }
    }
}