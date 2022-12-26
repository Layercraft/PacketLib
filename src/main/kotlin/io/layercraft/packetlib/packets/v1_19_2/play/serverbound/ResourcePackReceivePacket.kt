package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 *  | 0x24 | play | serverbound
 *
 * @property result result
 * @see <a href="https://wiki.vg/Protocol#Resource_Pack_.28serverbound.29">https://wiki.vg/Protocol#Resource_Pack_.28serverbound.29</a>
 */

@MinecraftPacket(packetId = 0x24, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class ResourcePackReceivePacket(
    val result: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<ResourcePackReceivePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ResourcePackReceivePacket {
            val result = input.readVarInt()

            return ResourcePackReceivePacket(result)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ResourcePackReceivePacket) {
            output.writeVarInt(value.result)
        }
    }
}