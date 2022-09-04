package io.layercraft.translator.packets.server.login

import io.layercraft.translator.serialization.*
import kotlinx.serialization.Serializable

/**
 * (Optional) Set compression | client-bound | Packet ID 0x03 | State: Login | Answer with nothing
 *
 * @property threshold Maximum size of a packet before it is compressed.
 * @see <a href="https://wiki.vg/Protocol#Set_Compression">https://wiki.vg/Protocol#Set_Compression</a>
 */
@Serializable
data class SetCompression(
    @MinecraftArray(MinecraftArraySizeType.VARINT)
    val threshold: Int
)
