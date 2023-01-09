package io.layercraft.packetlib.packets.v1_19_3.login.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Login Plugin Response | 0x02 | login | serverbound
 *
 * @param messageId messageId
 * @param successful successful
 * @param hasData data is present
 * @param data data
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Login_Plugin_Response">https://wiki.vg/Protocol#Login_Plugin_Response</a>
 */

@MinecraftPacket(id = 0x02, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
data class LoginPluginResponsePacket(
    val messageId: Int, // varint
    val successful: Boolean,
    val hasData: Boolean,
    val data: ByteArray?,
) : ServerBoundPacket {
    companion object : PacketSerializer<LoginPluginResponsePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): LoginPluginResponsePacket {
            val messageId = input.readVarInt()
            val successful = input.readBoolean()
            val hasData = input.readBoolean()
            val data = if (hasData) input.readRemainingByteArray() else null

            return LoginPluginResponsePacket(messageId, successful, hasData, data)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: LoginPluginResponsePacket) {
            output.writeVarInt(value.messageId)
            output.writeBoolean(value.successful)
            output.writeBoolean(value.hasData)
            if (value.hasData) output.writeRemainingByteArray(value.data!!)
        }
    }
}