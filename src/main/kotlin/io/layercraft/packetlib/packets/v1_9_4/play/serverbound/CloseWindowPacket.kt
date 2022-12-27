package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 *  | 0x08 | play | serverbound
 *
 * @property windowId windowId
 * @see <a href="https://wiki.vg/Protocol#Close_Window_.28serverbound.29">https://wiki.vg/Protocol#Close_Window_.28serverbound.29</a>
 */

@MinecraftPacket(packetId = 0x08, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class CloseWindowPacket(
    val windowId: UByte,
) : ServerBoundPacket {

    companion object : PacketSerializer<CloseWindowPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): CloseWindowPacket {
            val windowId = input.readUByte()

            return CloseWindowPacket(windowId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: CloseWindowPacket) {
            output.writeUByte(value.windowId)
        }
    }
}
