package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 *  | 0x37 | play | clientbound
 *
 * @property slot slot
 * @see <a href="https://wiki.vg/Protocol#Held_Item_Change_.28clientbound.29">https://wiki.vg/Protocol#Held_Item_Change_.28clientbound.29</a>
 */

@MinecraftPacket(packetId = 0x37, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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
