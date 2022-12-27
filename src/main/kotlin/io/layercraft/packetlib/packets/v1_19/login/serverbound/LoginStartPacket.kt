package io.layercraft.packetlib.packets.v1_19.login.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Login Start | 0x00 | login | serverbound
 *
 * @property username username
 * @see <a href="https://wiki.vg/Protocol#Login_Start">https://wiki.vg/Protocol#Login_Start</a>
 */

@MinecraftPacket(id = 0x00, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
data class LoginStartPacket(
    val username: String,
) : ServerBoundPacket {

    companion object : PacketSerializer<LoginStartPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): LoginStartPacket {
            val username = input.readString()

            return LoginStartPacket(username)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: LoginStartPacket) {
            output.writeString(value.username)
        }
    }
}