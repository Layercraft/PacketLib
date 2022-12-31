package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Pick Item | 0x1a | play | serverbound
 *
 * @property slot slot
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Pick_Item">https://wiki.vg/Protocol#Pick_Item</a>
 */

@MinecraftPacket(id = 0x1a, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class PickItemPacket(
    val slot: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<PickItemPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): PickItemPacket {
            val slot = input.readVarInt()

            return PickItemPacket(slot)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: PickItemPacket) {
            output.writeVarInt(value.slot)
        }
    }
}