package io.layercraft.translator.packets.server.login

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc

/**
 * (Optional) Set compression | 0x03 | login | client-bound
 *
 * @property threshold VarInt - Maximum size of a packet before it is compressed.
 * @see <a href="https://wiki.vg/Protocol#Set_Compression">https://wiki.vg/Protocol#Set_Compression</a>
 */
@MinecraftPacket(packetId = 0x03, state = PacketState.LOGIN, direction = PacketDirection.CLIENTBOUND)
data class SetCompression(
    val threshold: Int
): ServerPacket {

    companion object: PacketSerializer<SetCompression> {

        override fun serialize(input: Input): SetCompression {
            val threshold = input.mc.readVarInt()

            return SetCompression(threshold)
        }

        override fun deserialize(output: Output, value: SetCompression) {
            output.mc.writeVarInt(value.threshold)
        }
    }
}
