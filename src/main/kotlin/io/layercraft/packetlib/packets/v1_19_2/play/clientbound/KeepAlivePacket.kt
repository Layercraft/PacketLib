package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 *  | 0x20 | play | clientbound
 *
 * @property keepAliveId keepAliveId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Keep_Alive_.28clientbound.29">https://wiki.vg/Protocol#Keep_Alive_.28clientbound.29</a>
 */

@MinecraftPacket(id = 0x20, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class KeepAlivePacket(
    val keepAliveId: Long,
) : ClientBoundPacket {

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