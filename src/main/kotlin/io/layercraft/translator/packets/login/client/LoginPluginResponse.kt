package io.layercraft.translator.packets.login.client

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc

/**
 * Login plugin response | 0x02 | login | server-bound
 *
 * @property messageId VarInt - Should match ID from server.
 * @property successful Boolean - true if the client understood the request, false otherwise. When false, no payload follows.
 * @property data Optional Byte Array - Any data, depending on the channel. The length of this array must be inferred from the packet length.
 * @see <a href="https://wiki.vg/Protocol#Login_Plugin_Response">https://wiki.vg/Protocol#Login_Plugin_Response</a>
 */
@MinecraftPacket(packetId = 0x02, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
data class LoginPluginResponse(
    val messageId: Int,
    val successful: Boolean,
    val data: ByteArray
): ServerBoundPacket {
    companion object: PacketSerializer<LoginPluginResponse> {

        override fun serialize(input: Input): LoginPluginResponse {
            val messageId = input.mc.readVarInt()
            val successful = input.mc.readBoolean()
            val data = input.mc.readRemainingByteArray()

            return LoginPluginResponse(messageId, successful, data)
        }

        override fun deserialize(output: Output, value: LoginPluginResponse) {
            output.mc.writeVarInt(value.messageId)
            output.mc.writeBoolean(value.successful)
            output.mc.writeRemainingByteArray(value.data)
        }
    }
}
