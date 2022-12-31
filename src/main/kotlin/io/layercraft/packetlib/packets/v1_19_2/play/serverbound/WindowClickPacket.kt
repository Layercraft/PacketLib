package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Click Container | 0x0b | play | serverbound
 *
 * @property windowId windowId
 * @property stateId stateId
 * @property slot slot
 * @property mouseButton mouseButton
 * @property mode mode
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Click_Container">https://wiki.vg/Protocol#Click_Container</a>
 */

@MinecraftPacket(id = 0x0b, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class WindowClickPacket(
    val windowId: UByte,
    val stateId: Int, // varint
    val slot: Short,
    val mouseButton: Byte,
    val mode: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<WindowClickPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): WindowClickPacket {
            val windowId = input.readUByte()
            val stateId = input.readVarInt()
            val slot = input.readShort()
            val mouseButton = input.readByte()
            val mode = input.readVarInt()

            return WindowClickPacket(windowId, stateId, slot, mouseButton, mode)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: WindowClickPacket) {
            output.writeUByte(value.windowId)
            output.writeVarInt(value.stateId)
            output.writeShort(value.slot)
            output.writeByte(value.mouseButton)
            output.writeVarInt(value.mode)
        }
    }
}