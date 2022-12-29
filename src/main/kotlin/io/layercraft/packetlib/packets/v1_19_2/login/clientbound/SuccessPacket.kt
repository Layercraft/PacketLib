package io.layercraft.packetlib.packets.v1_19_2.login.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Login Success | 0x02 | login | clientbound
 *
 * @property uuid uuid
 * @property username username
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Login_Success">https://wiki.vg/Protocol#Login_Success</a>
 */

@MinecraftPacket(id = 0x02, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class SuccessPacket(
    val uuid: UUID,
    val username: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<SuccessPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SuccessPacket {
            val uuid = input.readUUID()
            val username = input.readString()

            return SuccessPacket(uuid, username)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SuccessPacket) {
            output.writeUUID(value.uuid)
            output.writeString(value.username)
        }
    }
}