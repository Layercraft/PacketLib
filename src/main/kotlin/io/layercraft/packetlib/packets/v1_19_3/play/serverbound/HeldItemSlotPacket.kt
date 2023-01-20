package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Held Item | 0x28 | play | serverbound
 *
 * @param slotId slotId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17935#Set_Held_Item_2">https://wiki.vg/Protocol#Set_Held_Item_2</a>
 */

@MinecraftPacket(id = 0x28, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class HeldItemSlotPacket(
    val slotId: Byte,
) : ServerBoundPacket {
    companion object : PacketSerializer<HeldItemSlotPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): HeldItemSlotPacket {
            val slotId = input.readByte()

            return HeldItemSlotPacket(slotId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: HeldItemSlotPacket) {
            output.writeByte(value.slotId)
        }
    }
}