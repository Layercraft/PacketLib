package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x0f | play | clientbound
 *
 * @property rootIndex rootIndex
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x0f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class DeclareCommandsPacket(
    val rootIndex: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<DeclareCommandsPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): DeclareCommandsPacket {
            val rootIndex = input.readVarInt()

            return DeclareCommandsPacket(rootIndex)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: DeclareCommandsPacket) {
            output.writeVarInt(value.rootIndex)
        }
    }
}