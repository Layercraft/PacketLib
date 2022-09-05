package io.layercraft.translator.packets.client.login

import io.layercraft.translator.packets.ClientPacket
import io.layercraft.translator.serialization.processing.MinecraftArray
import io.layercraft.translator.serialization.processing.MinecraftArraySizeType

data class EncryptionResponse(
    @MinecraftArray(MinecraftArraySizeType.VARINT)
    val sharedSecret: ByteArray,
    @MinecraftArray(MinecraftArraySizeType.VARINT)
    val verifyToken: ByteArray
) : ClientPacket
