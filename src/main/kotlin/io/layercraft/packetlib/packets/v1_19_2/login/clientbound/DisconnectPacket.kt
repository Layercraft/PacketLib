package io.layercraft.packetlib.packets.v1_19_2.login.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 *  | 0x00 | login | clientbound
 *
 * @property reason reason
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Disconnect_.28login.29">https://wiki.vg/Protocol#Disconnect_.28login.29</a>
 */

@MinecraftPacket(id = 0x00, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class DisconnectPacket(
    val reason: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<DisconnectPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): DisconnectPacket {
            val reason = input.readString()

            return DisconnectPacket(reason)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: DisconnectPacket) {
            output.writeString(value.reason)
        }
    }
}