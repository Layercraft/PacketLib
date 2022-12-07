package io.layercraft.translator.packets.status.serverbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Status request | 0x00 | status | server-bound
 *
 * @see <a href="https://wiki.vg/Protocol#Status_Request">https://wiki.vg/Protocol#Status_Request</a>
 */
@MinecraftPacket(packetId = 0x00, state = PacketState.STATUS, direction = PacketDirection.SERVERBOUND)
class StatusRequestPacket : ServerBoundPacket {
    companion object : PacketSerializer<StatusRequestPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): StatusRequestPacket {
            return StatusRequestPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: StatusRequestPacket) {}
    }
}