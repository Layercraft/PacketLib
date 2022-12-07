package io.layercraft.translator.packets.login.serverbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

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
    val messageId: Int, // varint
    val successful: Boolean,
    val data: ByteArray, // byte array remaining
) : ServerBoundPacket {
    companion object : PacketSerializer<LoginPluginResponse> {

        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): LoginPluginResponse {
            val messageId = input.readVarInt()
            val successful = input.readBoolean()
            val data = input.readRemainingByteArray()

            return LoginPluginResponse(messageId, successful, data)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: LoginPluginResponse) {
            output.writeVarInt(value.messageId)
            output.writeBoolean(value.successful)
            output.writeRemainingByteArray(value.data)
        }
    }
}