package io.layercraft.translator.packets.login.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * (Optional) Set compression | 0x03 | login | client-bound
 *
 * @property threshold Maximum size of a packet before it is compressed.
 * @see <a href="https://wiki.vg/Protocol#Set_Compression">https://wiki.vg/Protocol#Set_Compression</a>
 */
@MinecraftPacket(packetId = 0x03, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class SetCompressionPacket(
    val threshold: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<SetCompressionPacket> {

        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SetCompressionPacket {
            val threshold = input.readVarInt()

            return SetCompressionPacket(threshold)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SetCompressionPacket) {
            output.writeVarInt(value.threshold)
        }
    }
}