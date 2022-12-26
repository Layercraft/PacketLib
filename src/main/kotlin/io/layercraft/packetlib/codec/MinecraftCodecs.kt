package io.layercraft.packetlib.codec

import io.layercraft.packetlib.data.ProtocolVersion
import io.layercraft.packetlib.packets.PacketState

object MinecraftCodecs {
    val V_1_19_2: MinecraftCodec =
        MinecraftCodec.create(ProtocolVersion.V_1_19_2)
            .registerPacketRegistry(
                PacketState.HANDSHAKING,
                MinecraftCodecRegistry.create()

            )
            .registerPacketRegistry(
                PacketState.LOGIN,
                MinecraftCodecRegistry.create()

            )
            .registerPacketRegistry(
                PacketState.STATUS,
                MinecraftCodecRegistry.create()

            )
            .registerPacketRegistry(
                PacketState.PLAY,
                MinecraftCodecRegistry.create()


            )
}