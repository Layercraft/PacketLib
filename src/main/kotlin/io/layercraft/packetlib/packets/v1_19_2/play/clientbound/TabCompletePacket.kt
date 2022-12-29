package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x0e | play | clientbound
 *
 * @property transactionId transactionId
 * @property start start
 * @property length length
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x0e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class TabCompletePacket(
    val transactionId: Int, // varint
    val start: Int, // varint
    val length: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<TabCompletePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): TabCompletePacket {
            val transactionId = input.readVarInt()
            val start = input.readVarInt()
            val length = input.readVarInt()

            return TabCompletePacket(transactionId, start, length)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: TabCompletePacket) {
            output.writeVarInt(value.transactionId)
            output.writeVarInt(value.start)
            output.writeVarInt(value.length)
        }
    }
}