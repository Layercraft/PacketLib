package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import io.layercraft.packetlib.types.NBT
/**
 * Tag Query Response | 0x62 | play | clientbound
 *
 * @param transactionId transactionId
 * @param nbt nbt
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Tag_Query_Response">https://wiki.vg/Protocol#Tag_Query_Response</a>
 */

data class NbtQueryResponsePacket(
    val transactionId: Int, // varint
    val nbt: NBT,
) : ClientBoundPacket {
    companion object : PacketSerializer<NbtQueryResponsePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): NbtQueryResponsePacket {
            val transactionId = input.readVarInt()
            val nbt = input.readNbt()

            return NbtQueryResponsePacket(transactionId, nbt)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: NbtQueryResponsePacket) {
            output.writeVarInt(value.transactionId)
            output.writeNbt(value.nbt)
        }
    }
}