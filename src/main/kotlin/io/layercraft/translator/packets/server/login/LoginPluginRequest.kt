package io.layercraft.translator.packets.server.login

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc

/**
 * Login plugin request | 0x04 | login | client-bound
 *
 * @property messageId VarInt - Generated by the server - should be unique to the connection.
 * @property channel Identifier - Name of the plugin channel used to send the data.
 * @property data Reaming ByteArray - Any data, depending on the channel. The length of this array must be inferred from the packet length.
 * @see <a href="http://wiki.vg/Protocol#Login_Plugin_Request">https://wiki.vg/Protocol#Login_Plugin_Request</a>
 */
@MinecraftPacket(packetId = 0x04, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class LoginPluginRequest(
    val messageId: Int,
    val channel: String,
    val data: ByteArray
): ServerPacket {
    companion object: PacketSerializer<LoginPluginRequest> {

        override fun serialize(input: Input): LoginPluginRequest {
            val messageId = input.mc.readVarInt()
            val channel = input.mc.readIdentifier()
            val data = input.mc.readRemainingByteArray()

            return LoginPluginRequest(messageId, channel, data)
        }

        override fun deserialize(output: Output, value: LoginPluginRequest) {
            output.mc.writeVarInt(value.messageId)
            output.mc.writeIdentifier(value.channel)
            output.mc.writeRemainingByteArray(value.data)
        }
    }
}
