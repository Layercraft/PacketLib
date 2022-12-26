package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 *  | 0x11 | play | serverbound
 *
 * @property keepAliveId keepAliveId
 * @see <a href="https://wiki.vg/Protocol#Keep_Alive_.28serverbound.29">https://wiki.vg/Protocol#Keep_Alive_.28serverbound.29</a>
 */

@MinecraftPacket(packetId = 0x11, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class KeepAlivePacket(
    val keepAliveId: Long,
) : ServerBoundPacket {

    companion object : PacketSerializer<KeepAlivePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): KeepAlivePacket {
            val keepAliveId = input.readLong()

            return KeepAlivePacket(keepAliveId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: KeepAlivePacket) {
            output.writeLong(value.keepAliveId)
        }
    }
}