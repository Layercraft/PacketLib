package io.layercraft.packetlib.packets.v1_19_2.status.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Status Request | 0x00 | status | serverbound
 *

 * @see <a href="https://wiki.vg/Protocol#Status_Request">https://wiki.vg/Protocol#Status_Request</a>
 */

@MinecraftPacket(packetId = 0x00, state = PacketState.STATUS, direction = PacketDirection.SERVERBOUND)
class PingStartPacket() : ServerBoundPacket {

    companion object : PacketSerializer<PingStartPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): PingStartPacket {
            return PingStartPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: PingStartPacket) {
        }
    }
}