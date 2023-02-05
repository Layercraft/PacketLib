package io.layercraft.packetlib.packets.v1_19_3.status.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Status Request | 0x00 | status | serverbound
 *

 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17964#Status_Request">https://wiki.vg/Protocol#Status_Request</a>
 */

@MinecraftPacket(id = 0x00, state = PacketState.STATUS, direction = PacketDirection.SERVERBOUND)
class PingStartPacket() : ServerBoundPacket {
    companion object : PacketSerializer<PingStartPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): PingStartPacket {
            return PingStartPacket()
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: PingStartPacket) {
        }
    }
}