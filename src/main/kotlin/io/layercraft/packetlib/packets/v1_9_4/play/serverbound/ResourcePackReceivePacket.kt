package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Resource Pack Status | 0x16 | play | serverbound
 *
 * @property hash hash
 * @property result result
 * @see <a href="https://wiki.vg/Protocol#Resource_Pack_Status">https://wiki.vg/Protocol#Resource_Pack_Status</a>
 */

@MinecraftPacket(id = 0x16, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class ResourcePackReceivePacket(
    val hash: String,
    val result: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<ResourcePackReceivePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ResourcePackReceivePacket {
            val hash = input.readString()
            val result = input.readVarInt()

            return ResourcePackReceivePacket(hash, result)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ResourcePackReceivePacket) {
            output.writeString(value.hash)
            output.writeVarInt(value.result)
        }
    }
}
