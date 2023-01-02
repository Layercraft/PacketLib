package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Held Item (clientbound) | 0x4a | play | clientbound
 *
 * @param slot slot
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Set_Held_Item_.28clientbound.29">https://wiki.vg/Protocol#Set_Held_Item_.28clientbound.29</a>
 */

@MinecraftPacket(id = 0x4a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class HeldItemSlotPacket(
    val slot: Byte,
) : ClientBoundPacket {
    companion object : PacketSerializer<HeldItemSlotPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): HeldItemSlotPacket {
            val slot = input.readByte()

            return HeldItemSlotPacket(slot)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: HeldItemSlotPacket) {
            output.writeByte(value.slot)
        }
    }
}