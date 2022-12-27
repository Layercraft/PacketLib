package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 *  | 0x12 | play | clientbound
 *
 * @property windowId windowId
 * @see <a href="https://wiki.vg/Protocol#Close_Window_.28clientbound.29">https://wiki.vg/Protocol#Close_Window_.28clientbound.29</a>
 */

@MinecraftPacket(id = 0x12, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class CloseWindowPacket(
    val windowId: UByte,
) : ClientBoundPacket {

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
