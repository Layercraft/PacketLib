package io.layercraft.translator.packets.login.serverbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc

/**
 * Login plugin response | 0x02 | login | server-bound
 *
 * @property messageId Should match ID from server.
 * @property successful true if the client understood the request, false otherwise. When false, no payload follows.
 * @property data Any data, depending on the channel. The length of this array must be inferred from the packet length.
 * @see <a href="https://wiki.vg/Protocol#Login_Plugin_Response">https://wiki.vg/Protocol#Login_Plugin_Response</a>
 */
@MinecraftPacket(packetId = 0x02, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
data class LoginPluginResponse(
    val messageId: Int, //varint
    val successful: Boolean,
    val data: ByteArray, //byte array remaining
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
