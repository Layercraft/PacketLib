package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 *  | 0x12 | play | serverbound
 *
 * @property keepAliveId keepAliveId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Keep_Alive_.28serverbound.29">https://wiki.vg/Protocol#Keep_Alive_.28serverbound.29</a>
 */

@MinecraftPacket(id = 0x12, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class KeepAlivePacket(
    val keepAliveId: Long,
) : ServerBoundPacket {
    companion object : PacketSerializer<KeepAlivePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): KeepAlivePacket {
            val keepAliveId = input.readLong()

            return KeepAlivePacket(keepAliveId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: KeepAlivePacket) {
            output.writeLong(value.keepAliveId)
        }
    }
}