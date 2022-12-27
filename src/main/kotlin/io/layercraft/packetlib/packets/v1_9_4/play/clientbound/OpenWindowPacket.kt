package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Open Window | 0x13 | play | clientbound
 *
 * @property windowId windowId
 * @property inventoryType inventoryType
 * @property windowTitle windowTitle
 * @property slotCount slotCount
 * @see <a href="https://wiki.vg/Protocol#Open_Window">https://wiki.vg/Protocol#Open_Window</a>
 */

@MinecraftPacket(packetId = 0x13, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class OpenWindowPacket(
    val windowId: UByte,
    val inventoryType: String,
    val windowTitle: String,
    val slotCount: UByte,
) : ClientBoundPacket {

    companion object : PacketSerializer<OpenWindowPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): OpenWindowPacket {
            val windowId = input.readUByte()
            val inventoryType = input.readString()
            val windowTitle = input.readString()
            val slotCount = input.readUByte()

            return OpenWindowPacket(windowId, inventoryType, windowTitle, slotCount)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: OpenWindowPacket) {
            output.writeUByte(value.windowId)
            output.writeString(value.inventoryType)
            output.writeString(value.windowTitle)
            output.writeUByte(value.slotCount)
        }
    }
}
