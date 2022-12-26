package io.layercraft.packetlib.packets.v1_19.login.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Login Plugin Response | 0x02 | login | serverbound
 *
 * @property messageId messageId
 * @see <a href="https://wiki.vg/Protocol#Login_Plugin_Response">https://wiki.vg/Protocol#Login_Plugin_Response</a>
 */

@MinecraftPacket(packetId = 0x02, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
data class LoginPluginResponsePacket(
    val messageId: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<LoginPluginResponsePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): LoginPluginResponsePacket {
            val messageId = input.readVarInt()

            return LoginPluginResponsePacket(messageId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: LoginPluginResponsePacket) {
            output.writeVarInt(value.messageId)
        }
    }
}