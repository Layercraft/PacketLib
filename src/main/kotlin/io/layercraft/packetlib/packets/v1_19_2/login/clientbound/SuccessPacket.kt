package io.layercraft.packetlib.packets.v1_19_2.login.clientbound

import io.layercraft.packetlib.packets.ClientBoundPacket
import io.layercraft.packetlib.packets.MinecraftPacket
import io.layercraft.packetlib.packets.PacketDirection
import io.layercraft.packetlib.packets.PacketSerializer
import io.layercraft.packetlib.packets.PacketState
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.*

/**
 * Login Success | 0x02 | login | clientbound
 *
 * @property uuid uuid
 * @property username username
 * @see <a href="https://wiki.vg/Protocol#Login_Success">https://wiki.vg/Protocol#Login_Success</a>
 */

@MinecraftPacket(packetId = 0x02, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class SuccessPacket(
    val uuid: UUID,
    val username: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<SuccessPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SuccessPacket {
            val uuid = input.readUUID()
            val username = input.readString()

            return SuccessPacket(uuid, username)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SuccessPacket) {
            output.writeUUID(value.uuid)
            output.writeString(value.username)
        }
    }
}