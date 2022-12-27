package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Window Items | 0x14 | play | clientbound
 *
 * @property windowId windowId
 * @see <a href="https://wiki.vg/Protocol#Window_Items">https://wiki.vg/Protocol#Window_Items</a>
 */

@MinecraftPacket(id = 0x14, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class WindowItemsPacket(
    val windowId: UByte,
) : ClientBoundPacket {

    companion object : PacketSerializer<WindowItemsPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): WindowItemsPacket {
            val windowId = input.readUByte()

            return WindowItemsPacket(windowId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: WindowItemsPacket) {
            output.writeUByte(value.windowId)
        }
    }
}
