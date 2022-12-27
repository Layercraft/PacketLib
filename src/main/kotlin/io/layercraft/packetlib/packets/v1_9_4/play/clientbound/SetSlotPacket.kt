package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Set Slot | 0x16 | play | clientbound
 *
 * @property windowId windowId
 * @property slot slot
 * @see <a href="https://wiki.vg/Protocol#Set_Slot">https://wiki.vg/Protocol#Set_Slot</a>
 */

@MinecraftPacket(id = 0x16, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SetSlotPacket(
    val windowId: Byte,
    val slot: Short,
) : ClientBoundPacket {

    companion object : PacketSerializer<SetSlotPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SetSlotPacket {
            val windowId = input.readByte()
            val slot = input.readShort()

            return SetSlotPacket(windowId, slot)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SetSlotPacket) {
            output.writeByte(value.windowId)
            output.writeShort(value.slot)
        }
    }
}
