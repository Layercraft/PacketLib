package io.layercraft.translator.codec

import io.layercraft.translator.data.ProtocolVersion
import io.layercraft.translator.packets.PacketState
import io.layercraft.translator.packets.handshake.serverbound.HandshakePacket
import io.layercraft.translator.packets.login.clientbound.*
import io.layercraft.translator.packets.login.serverbound.EncryptionResponsePacket
import io.layercraft.translator.packets.login.serverbound.LoginPluginResponsePacket
import io.layercraft.translator.packets.login.serverbound.LoginStartPacket
import io.layercraft.translator.packets.play.clientbound.*
import io.layercraft.translator.packets.status.clientbound.PingResponsePacket
import io.layercraft.translator.packets.status.clientbound.StatusResponsePacket
import io.layercraft.translator.packets.status.serverbound.PingRequestPacket
import io.layercraft.translator.packets.status.serverbound.StatusRequestPacket

object MinecraftCodecs {
    val V_1_19_2: MinecraftCodec =
        MinecraftCodec.create(ProtocolVersion.V_1_19_2)
            .registerPacketRegistry(
                PacketState.HANDSHAKE,
                MinecraftCodecRegistry.create()
                    .registerServerBoundPacket(0x00, HandshakePacket::class, HandshakePacket),
            )
            .registerPacketRegistry(
                PacketState.LOGIN,
                MinecraftCodecRegistry.create()
                    .registerServerBoundPacket(0x00, LoginStartPacket::class, LoginStartPacket)
                    .registerServerBoundPacket(0x01, EncryptionResponsePacket::class, EncryptionResponsePacket)
                    .registerServerBoundPacket(0x02, LoginPluginResponsePacket::class, LoginPluginResponsePacket)
                    .registerClientBoundPacket(0x00, DisconnectPacket::class, DisconnectPacket)
                    .registerClientBoundPacket(0x01, EncryptionRequestPacket::class, EncryptionRequestPacket)
                    .registerClientBoundPacket(0x02, LoginSuccessPacket::class, LoginSuccessPacket)
                    .registerClientBoundPacket(0x03, SetCompressionPacket::class, SetCompressionPacket)
                    .registerClientBoundPacket(0x04, LoginPluginRequestPacket::class, LoginPluginRequestPacket),
            )
            .registerPacketRegistry(
                PacketState.STATUS,
                MinecraftCodecRegistry.create()
                    .registerServerBoundPacket(0x00, StatusRequestPacket::class, StatusRequestPacket)
                    .registerServerBoundPacket(0x01, PingRequestPacket::class, PingRequestPacket)
                    .registerClientBoundPacket(0x00, StatusResponsePacket::class, StatusResponsePacket)
                    .registerClientBoundPacket(0x01, PingResponsePacket::class, PingResponsePacket),
            )
            .registerPacketRegistry(
                PacketState.PLAY,
                MinecraftCodecRegistry.create()
                    .registerClientBoundPacket(0x00, SpawnEntityPacket::class, SpawnEntityPacket)
                    .registerClientBoundPacket(0x01, SpawnExperienceOrbPacket::class, SpawnExperienceOrbPacket)
                    .registerClientBoundPacket(0x02, SpawnPlayerPacket::class, SpawnPlayerPacket)
                    .registerClientBoundPacket(0x03, EntityAnimationPacket::class, EntityAnimationPacket)
                    .registerClientBoundPacket(0x04, AwardStatisticsPacket::class, AwardStatisticsPacket)
                    .registerClientBoundPacket(0x05, AcknowledgeBlockChangePacket::class, AcknowledgeBlockChangePacket)
                    .registerClientBoundPacket(0x06, SetBlockDestroyStagePacket::class, SetBlockDestroyStagePacket)
                    .registerClientBoundPacket(0x07, BlockEntityDataPacket::class, BlockEntityDataPacket)
                    .registerClientBoundPacket(0x08, BlockActionPacket::class, BlockActionPacket)
                    .registerClientBoundPacket(0x0A, BossBarPacket::class, BossBarPacket)
                    .registerClientBoundPacket(0x0B, ChangeDifficultyPacket::class, ChangeDifficultyPacket)
                    .registerClientBoundPacket(0x0C, ChatPreviewPacket::class, ChatPreviewPacket)
                    .registerClientBoundPacket(0x0D, ClearTitlesPacket::class, ClearTitlesPacket)
                    .registerClientBoundPacket(0x0E, CommandSuggestionsResponsePacket::class, CommandSuggestionsResponsePacket)
                    .registerClientBoundPacket(0x25, LoginPacket::class, LoginPacket),

            )
}