package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Creative Inventory Action | 0x18 | play | serverbound
 *
 * @property slot slot
 * @see <a href="https://wiki.vg/Protocol#Creative_Inventory_Action">https://wiki.vg/Protocol#Creative_Inventory_Action</a>
 */

@MinecraftPacket(packetId = 0x18, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class SetCreativeSlotPacket(
    val slot: Short,
) : ServerBoundPacket {

    companion object : PacketSerializer<SetCreativeSlotPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SetCreativeSlotPacket {
            val slot = input.readShort()

            return SetCreativeSlotPacket(slot)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SetCreativeSlotPacket) {
            output.writeShort(value.slot)
        }
    }
}
