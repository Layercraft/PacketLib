package io.layercraft.translator.packets.login.clientbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.translator.utils.minecraft

/**
 * (Optional) Set compression | 0x03 | login | client-bound
 *
 * @property threshold Maximum size of a packet before it is compressed.
 * @see <a href="https://wiki.vg/Protocol#Set_Compression">https://wiki.vg/Protocol#Set_Compression</a>
 */
@MinecraftPacket(packetId = 0x03, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class SetCompression(
    val threshold: Int, //varint
): ClientBoundPacket {

    companion object: PacketSerializer<SetCompression> {

        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SetCompression {
            val threshold = input.readVarInt()

            return SetCompression(threshold)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SetCompression) {
            output.writeVarInt(value.threshold)
        }
    }
}
