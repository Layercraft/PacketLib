package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x4a | play | clientbound
 *
 * @property slot slot
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x4a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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