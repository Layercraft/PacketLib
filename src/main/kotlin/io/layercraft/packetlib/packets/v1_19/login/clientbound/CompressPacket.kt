package io.layercraft.packetlib.packets.v1_19.login.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Compression | 0x03 | login | clientbound
 *
 * @property threshold threshold
 * @see <a href="https://wiki.vg/Protocol#Set_Compression">https://wiki.vg/Protocol#Set_Compression</a>
 */

@MinecraftPacket(id = 0x03, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class CompressPacket(
    val threshold: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<CompressPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): CompressPacket {
            val threshold = input.readVarInt()

            return CompressPacket(threshold)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: CompressPacket) {
            output.writeVarInt(value.threshold)
        }
    }
}