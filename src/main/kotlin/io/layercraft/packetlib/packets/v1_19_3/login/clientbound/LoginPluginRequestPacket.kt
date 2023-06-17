package io.layercraft.packetlib.packets.v1_19_3.login.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Login Plugin Request | 0x04 | login | clientbound
 *
 * @param messageId messageId
 * @param channel channel
 * @param data data
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Login_Plugin_Request">https://wiki.vg/Protocol#Login_Plugin_Request</a>
 */

@MinecraftPacket(id = 0x04, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class LoginPluginRequestPacket(
    val messageId: Int, // varint
    val channel: String,
    val data: ByteArray,
) : ClientBoundPacket {
    companion object : PacketSerializer<LoginPluginRequestPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): LoginPluginRequestPacket {
            val messageId = input.readVarInt()
            val channel = input.readString()
            val data = input.readRemainingByteArray()

            return LoginPluginRequestPacket(messageId, channel, data)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: LoginPluginRequestPacket) {
            output.writeVarInt(value.messageId)
            output.writeString(value.channel)
            output.writeRemainingByteArray(value.data)
        }
    }
}