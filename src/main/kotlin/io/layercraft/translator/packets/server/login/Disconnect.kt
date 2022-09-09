package io.layercraft.translator.packets.server.login

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc


/**
 * Disconnect | 0x00 | login | client-bound
 *
 * @property reason Chat - The reason why the player was disconnected.
 * @see <a href="https://wiki.vg/Protocol#Disconnect">https://wiki.vg/Protocol#Disconnect</a>
 */
@MinecraftPacket(packetId = 0x00, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class Disconnect(
    val reason: String
): ServerPacket {
    companion object : PacketSerializer<Disconnect>{

        override fun serialize(input: Input): Disconnect {
            val reason = input.mc.readChat()

            return Disconnect(reason)
        }

        override fun deserialize(output: Output, value: Disconnect) {
            output.mc.writeChat(value.reason)
        }

    }
}
