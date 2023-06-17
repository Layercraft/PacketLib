package io.layercraft.packetlib.packets.v1_19_3.login.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Login Plugin Response | 0x02 | login | serverbound
 *
 * @param messageId messageId
 * @param hasData data is present
 * @param data data
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Login_Plugin_Response">https://wiki.vg/Protocol#Login_Plugin_Response</a>
 */

data class LoginPluginResponsePacket(
    val messageId: Int, // varint
    val hasData: Boolean,
    val data: ByteArray?,
) : ServerBoundPacket {
    companion object : PacketSerializer<LoginPluginResponsePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): LoginPluginResponsePacket {
            val messageId = input.readVarInt()
            val hasData = input.readBoolean()
            val data = if (hasData) input.readRemainingByteArray() else null

            return LoginPluginResponsePacket(messageId, hasData, data)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: LoginPluginResponsePacket) {
            output.writeVarInt(value.messageId)
            output.writeBoolean(value.hasData)
            if (value.hasData) output.writeRemainingByteArray(value.data!!)
        }
    }
}