package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Click Window | 0x07 | play | serverbound
 *
 * @property windowId windowId
 * @property slot slot
 * @property mouseButton mouseButton
 * @property action action
 * @property mode mode
 * @see <a href="https://wiki.vg/Protocol#Click_Window">https://wiki.vg/Protocol#Click_Window</a>
 */

@MinecraftPacket(packetId = 0x07, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class WindowClickPacket(
    val windowId: UByte,
    val slot: Short,
    val mouseButton: Byte,
    val action: Short,
    val mode: Byte,
) : ServerBoundPacket {

    companion object : PacketSerializer<WindowClickPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): WindowClickPacket {
            val windowId = input.readUByte()
            val slot = input.readShort()
            val mouseButton = input.readByte()
            val action = input.readShort()
            val mode = input.readByte()

            return WindowClickPacket(windowId, slot, mouseButton, action, mode)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: WindowClickPacket) {
            output.writeUByte(value.windowId)
            output.writeShort(value.slot)
            output.writeByte(value.mouseButton)
            output.writeShort(value.action)
            output.writeByte(value.mode)
        }
    }
}
