package io.layercraft.packetlib.packets.v1_19_2.login.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Login Plugin Request | 0x04 | login | clientbound
 *
 * @property messageId messageId
 * @property channel channel
 * @property data data
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Login_Plugin_Request">https://wiki.vg/Protocol#Login_Plugin_Request</a>
 */

@MinecraftPacket(id = 0x04, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class LoginPluginRequestPacket(
    val messageId: Int, // varint
    val channel: String,
    val data: ByteArray,
) : ClientBoundPacket {

    companion object : PacketSerializer<LoginPluginRequestPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): LoginPluginRequestPacket {
            val messageId = input.readVarInt()
            val channel = input.readString()
            val data = input.readRemainingByteArray()

            return LoginPluginRequestPacket(messageId, channel, data)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: LoginPluginRequestPacket) {
            output.writeVarInt(value.messageId)
            output.writeString(value.channel)
            output.writeRemainingByteArray(value.data)
        }
    }
}