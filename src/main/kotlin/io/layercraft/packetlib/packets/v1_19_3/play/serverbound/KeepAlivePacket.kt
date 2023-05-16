package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Keep Alive | 0x11 | play | serverbound
 *
 * @param keepAliveId keepAliveId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18071#Keep_Alive_2">https://wiki.vg/Protocol#Keep_Alive_2</a>
 */

@MinecraftPacket(id = 0x11, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
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