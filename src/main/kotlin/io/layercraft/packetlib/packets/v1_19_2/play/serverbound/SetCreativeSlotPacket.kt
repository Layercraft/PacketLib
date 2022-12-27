package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x2b | play | serverbound
 *
 * @property slot slot
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x2b, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
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