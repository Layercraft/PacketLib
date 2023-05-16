package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Select Trade | 0x26 | play | serverbound
 *
 * @param slot slot
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18071#Select_Trade">https://wiki.vg/Protocol#Select_Trade</a>
 */

@MinecraftPacket(id = 0x26, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class SelectTradePacket(
    val slot: Int, // varint
) : ServerBoundPacket {
    companion object : PacketSerializer<SelectTradePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SelectTradePacket {
            val slot = input.readVarInt()

            return SelectTradePacket(slot)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SelectTradePacket) {
            output.writeVarInt(value.slot)
        }
    }
}