package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Creative Mode Slot | 0x2b | play | serverbound
 *
 * @param slot slot
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17935#Set_Creative_Mode_Slot">https://wiki.vg/Protocol#Set_Creative_Mode_Slot</a>
 */

@MinecraftPacket(id = 0x2b, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class SetCreativeSlotPacket(
    val slot: Short,
) : ServerBoundPacket {
    companion object : PacketSerializer<SetCreativeSlotPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SetCreativeSlotPacket {
            val slot = input.readShort()

            return SetCreativeSlotPacket(slot)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SetCreativeSlotPacket) {
            output.writeShort(value.slot)
        }
    }
}