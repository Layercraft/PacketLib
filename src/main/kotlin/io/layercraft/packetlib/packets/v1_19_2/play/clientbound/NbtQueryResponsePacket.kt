package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.NBT
/**
 * Tag Query Response | 0x64 | play | clientbound
 *
 * @property transactionId transactionId
 * @property nbt nbt
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Tag_Query_Response">https://wiki.vg/Protocol#Tag_Query_Response</a>
 */

@MinecraftPacket(id = 0x64, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class NbtQueryResponsePacket(
    val transactionId: Int, // varint
    val nbt: NBT,
) : ClientBoundPacket {
    companion object : PacketSerializer<NbtQueryResponsePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): NbtQueryResponsePacket {
            val transactionId = input.readVarInt()
            val nbt = input.readNbt()

            return NbtQueryResponsePacket(transactionId, nbt)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: NbtQueryResponsePacket) {
            output.writeVarInt(value.transactionId)
            output.writeNbt(value.nbt)
        }
    }
}
