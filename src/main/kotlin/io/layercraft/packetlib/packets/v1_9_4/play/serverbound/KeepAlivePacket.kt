package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x0b | play | serverbound
 *
 * @property keepAliveId keepAliveId
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x0b, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class KeepAlivePacket(
    val keepAliveId: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<KeepAlivePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): KeepAlivePacket {
            val keepAliveId = input.readVarInt()

            return KeepAlivePacket(keepAliveId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: KeepAlivePacket) {
            output.writeVarInt(value.keepAliveId)
        }
    }
}
