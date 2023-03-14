package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Server Data | 0x41 | play | clientbound
 *
 * @param hasMotd motd is present
 * @param motd motd
 * @param hasIcon icon is present
 * @param icon icon
 * @param enforcesSecureChat enforcesSecureChat
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Server_Data">https://wiki.vg/Protocol#Server_Data</a>
 */

@MinecraftPacket(id = 0x41, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ServerDataPacket(
    val hasMotd: Boolean,
    val motd: String?,
    val hasIcon: Boolean,
    val icon: String?,
    val enforcesSecureChat: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<ServerDataPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ServerDataPacket {
            val hasMotd = input.readBoolean()
            val motd = if (hasMotd) input.readString() else null
            val hasIcon = input.readBoolean()
            val icon = if (hasIcon) input.readString() else null
            val enforcesSecureChat = input.readBoolean()

            return ServerDataPacket(hasMotd, motd, hasIcon, icon, enforcesSecureChat)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ServerDataPacket) {
            output.writeBoolean(value.hasMotd)
            if (value.hasMotd) output.writeString(value.motd!!)
            output.writeBoolean(value.hasIcon)
            if (value.hasIcon) output.writeString(value.icon!!)
            output.writeBoolean(value.enforcesSecureChat)
        }
    }
}