package io.layercraft.packetlib.packets.v1_9_4.login.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Login Success | 0x02 | login | clientbound
 *
 * @property uuid uuid
 * @property username username
 * @see <a href="https://wiki.vg/Protocol#Login_Success">https://wiki.vg/Protocol#Login_Success</a>
 */

@MinecraftPacket(id = 0x02, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class SuccessPacket(
    val uuid: String,
    val username: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<SuccessPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SuccessPacket {
            val uuid = input.readString()
            val username = input.readString()

            return SuccessPacket(uuid, username)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SuccessPacket) {
            output.writeString(value.uuid)
            output.writeString(value.username)
        }
    }
}
