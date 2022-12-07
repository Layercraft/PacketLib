package io.layercraft.translator.packets.login.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Disconnect | 0x00 | login | client-bound
 *
 * @property reason The reason why the player was disconnected.
 * @see <a href="https://wiki.vg/Protocol#Disconnect">https://wiki.vg/Protocol#Disconnect</a>
 */
@MinecraftPacket(packetId = 0x00, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class Disconnect(
    val reason: String, // chat
) : ClientBoundPacket {
    companion object : PacketSerializer<Disconnect> {

        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): Disconnect {
            val reason = input.readChat()

            return Disconnect(reason)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: Disconnect) {
            output.writeChat(value.reason)
        }
    }
}