package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 *  | 0x47 | play | clientbound
 *
 * @property slot slot
 * @see <a href="https://wiki.vg/Protocol#Set_Held_Item_.28clientbound.29">https://wiki.vg/Protocol#Set_Held_Item_.28clientbound.29</a>
 */

@MinecraftPacket(id = 0x47, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class HeldItemSlotPacket(
    val slot: Byte,
) : ClientBoundPacket {

    companion object : PacketSerializer<HeldItemSlotPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): HeldItemSlotPacket {
            val slot = input.readByte()

            return HeldItemSlotPacket(slot)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: HeldItemSlotPacket) {
            output.writeByte(value.slot)
        }
    }
}