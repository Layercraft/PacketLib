package io.layercraft.packetlib.packets.v1_19_2.handshaking.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Legacy Server List Ping | 0xfe | handshaking | serverbound
 *
 * @param payload payload
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Legacy_Server_List_Ping">https://wiki.vg/Protocol#Legacy_Server_List_Ping</a>
 */

@MinecraftPacket(id = 0xfe, state = PacketState.HANDSHAKING, direction = PacketDirection.SERVERBOUND)
data class LegacyServerListPingPacket(
    val payload: UByte,
) : ServerBoundPacket {
    companion object : PacketSerializer<LegacyServerListPingPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): LegacyServerListPingPacket {
            val payload = input.readUByte()

            return LegacyServerListPingPacket(payload)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: LegacyServerListPingPacket) {
            output.writeUByte(value.payload)
        }
    }
}