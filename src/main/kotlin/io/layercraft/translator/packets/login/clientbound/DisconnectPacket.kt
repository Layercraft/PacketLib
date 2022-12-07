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
data class DisconnectPacket(
    val reason: String, // chat
) : ClientBoundPacket {
    companion object : PacketSerializer<DisconnectPacket> {

        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): DisconnectPacket {
            val reason = input.readChat()

            return DisconnectPacket(reason)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: DisconnectPacket) {
            output.writeChat(value.reason)
        }
    }
}