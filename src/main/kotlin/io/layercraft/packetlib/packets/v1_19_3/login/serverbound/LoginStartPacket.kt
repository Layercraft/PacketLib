package io.layercraft.packetlib.packets.v1_19_3.login.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import java.util.UUID
/**
 * Login Start | 0x00 | login | serverbound
 *
 * @param username username
 * @param hasPlayerUUID playerUUID is present
 * @param playerUUID playerUUID
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Login_Start">https://wiki.vg/Protocol#Login_Start</a>
 */

data class LoginStartPacket(
    val username: String,
    val hasPlayerUUID: Boolean,
    val playerUUID: UUID?,
) : ServerBoundPacket {
    companion object : PacketSerializer<LoginStartPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): LoginStartPacket {
            val username = input.readString()
            val hasPlayerUUID = input.readBoolean()
            val playerUUID = if (hasPlayerUUID) input.readUUID() else null

            return LoginStartPacket(username, hasPlayerUUID, playerUUID)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: LoginStartPacket) {
            output.writeString(value.username)
            output.writeBoolean(value.hasPlayerUUID)
            if (value.hasPlayerUUID) output.writeUUID(value.playerUUID!!)
        }
    }
}