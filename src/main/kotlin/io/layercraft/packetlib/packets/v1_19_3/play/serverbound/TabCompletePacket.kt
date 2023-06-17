package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Command Suggestions Request | 0x08 | play | serverbound
 *
 * @param transactionId transactionId
 * @param text text
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Command_Suggestions_Request">https://wiki.vg/Protocol#Command_Suggestions_Request</a>
 */

@MinecraftPacket(id = 0x08, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class TabCompletePacket(
    val transactionId: Int, // varint
    val text: String,
) : ServerBoundPacket {
    companion object : PacketSerializer<TabCompletePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): TabCompletePacket {
            val transactionId = input.readVarInt()
            val text = input.readString()

            return TabCompletePacket(transactionId, text)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: TabCompletePacket) {
            output.writeVarInt(value.transactionId)
            output.writeString(value.text)
        }
    }
}