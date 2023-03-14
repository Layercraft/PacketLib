package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Held Item | 0x49 | play | clientbound
 *
 * @param slot slot
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Set_Held_Item">https://wiki.vg/Protocol#Set_Held_Item</a>
 */

@MinecraftPacket(id = 0x49, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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