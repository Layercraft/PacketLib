package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x0a | play | serverbound
 *
 * @property windowId windowId
 * @property stateId stateId
 * @property slot slot
 * @property mouseButton mouseButton
 * @property mode mode
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x0a, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class WindowClickPacket(
    val windowId: UByte,
    val stateId: Int, // varint
    val slot: Short,
    val mouseButton: Byte,
    val mode: Int, // varint
) : ServerBoundPacket {

    companion object : PacketSerializer<WindowClickPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): WindowClickPacket {
            val windowId = input.readUByte()
            val stateId = input.readVarInt()
            val slot = input.readShort()
            val mouseButton = input.readByte()
            val mode = input.readVarInt()

            return WindowClickPacket(windowId, stateId, slot, mouseButton, mode)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: WindowClickPacket) {
            output.writeUByte(value.windowId)
            output.writeVarInt(value.stateId)
            output.writeShort(value.slot)
            output.writeByte(value.mouseButton)
            output.writeVarInt(value.mode)
        }
    }
}