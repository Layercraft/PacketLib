package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 *  | 0x17 | play | serverbound
 *
 * @property slotId slotId
 * @see <a href="https://wiki.vg/Protocol#Held_Item_Change_.28serverbound.29">https://wiki.vg/Protocol#Held_Item_Change_.28serverbound.29</a>
 */

@MinecraftPacket(id = 0x17, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class HeldItemSlotPacket(
    val slotId: Short,
) : ServerBoundPacket {

    companion object : PacketSerializer<HeldItemSlotPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): HeldItemSlotPacket {
            val slotId = input.readShort()

            return HeldItemSlotPacket(slotId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: HeldItemSlotPacket) {
            output.writeShort(value.slotId)
        }
    }
}
