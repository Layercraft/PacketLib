package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Keep Alive | 0x1f | play | clientbound
 *
 * @param keepAliveId keepAliveId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17935#Keep_Alive">https://wiki.vg/Protocol#Keep_Alive</a>
 */

@MinecraftPacket(id = 0x1f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class KeepAlivePacket(
    val keepAliveId: Long,
) : ClientBoundPacket {
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