package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x1e | play | clientbound
 *
 * @property keepAliveId keepAliveId
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x1e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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