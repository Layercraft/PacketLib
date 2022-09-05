package io.layercraft.translator.packets.server.login

import io.layercraft.translator.packets.ServerPacket
import io.layercraft.translator.serialization.processing.MinecraftString
import io.layercraft.translator.utils.MINECRAFT_MAX_CHAT_LENGTH
import kotlinx.serialization.Serializable

/**
 * Disconnect (login) | client-bound | Packet ID 0x00 | State: Login | Answer to any login packet.
 *
 * @property reason The reason why the player was disconnected.
 * @see <a href="https://wiki.vg/Protocol#Disconnect_.28login.29">https://wiki.vg/Protocol#Disconnect_.28login.29</a>
 */
@Serializable
data class Disconnect(
    @MinecraftString(MINECRAFT_MAX_CHAT_LENGTH)
    val reason: String
): ServerPacket
