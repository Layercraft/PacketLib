package io.layercraft.translator.codec

import io.layercraft.translator.data.ProtocolVersion
import io.layercraft.translator.packets.PacketState
import io.layercraft.translator.packets.handshake.serverbound.Handshake
import io.layercraft.translator.packets.login.clientbound.*
import io.layercraft.translator.packets.login.serverbound.EncryptionResponse
import io.layercraft.translator.packets.login.serverbound.LoginPluginResponse
import io.layercraft.translator.packets.login.serverbound.LoginStart
import io.layercraft.translator.packets.play.clientbound.*
import io.layercraft.translator.packets.status.clientbound.PingResponse
import io.layercraft.translator.packets.status.clientbound.StatusResponse
import io.layercraft.translator.packets.status.serverbound.PingRequest
import io.layercraft.translator.packets.status.serverbound.StatusRequest

object MinecraftCodecs {
    val V_1_19_2: MinecraftCodec =
        MinecraftCodec.create(ProtocolVersion.V_1_19_2)
            .registerPacketRegistry(
                PacketState.HANDSHAKE,
                MinecraftCodecRegistry.create()
                    .registerServerBoundPacket(0x00, Handshake::class, Handshake)
            )
            .registerPacketRegistry(
                PacketState.LOGIN,
                MinecraftCodecRegistry.create()
                    .registerServerBoundPacket(0x00, LoginStart::class, LoginStart)
                    .registerServerBoundPacket(0x01, EncryptionResponse::class, EncryptionResponse)
                    .registerServerBoundPacket(0x02, LoginPluginResponse::class, LoginPluginResponse)
                    .registerClientBoundPacket(0x00, Disconnect::class, Disconnect)
                    .registerClientBoundPacket(0x01, EncryptionRequest::class, EncryptionRequest)
                    .registerClientBoundPacket(0x02, LoginSuccess::class, LoginSuccess)
                    .registerClientBoundPacket(0x03, SetCompression::class, SetCompression)
                    .registerClientBoundPacket(0x04, LoginPluginRequest::class, LoginPluginRequest)
            )
            .registerPacketRegistry(
                PacketState.STATUS,
                MinecraftCodecRegistry.create()
                    .registerServerBoundPacket(0x00, StatusRequest::class, StatusRequest)
                    .registerServerBoundPacket(0x01, PingRequest::class, PingRequest)
                    .registerClientBoundPacket(0x00, StatusResponse::class, StatusResponse)
                    .registerClientBoundPacket(0x01, PingResponse::class, PingResponse)
            )
            .registerPacketRegistry(
                PacketState.PLAY,
                MinecraftCodecRegistry.create()
                    .registerClientBoundPacket(0x00, SpawnEntity::class, SpawnEntity)
                    .registerClientBoundPacket(0x01, SpawnExperienceOrb::class, SpawnExperienceOrb)
                    .registerClientBoundPacket(0x02, SpawnPlayer::class, SpawnPlayer)
                    .registerClientBoundPacket(0x03, EntityAnimation::class, EntityAnimation)
                    .registerClientBoundPacket(0x04, AwardStatistics::class, AwardStatistics)
                    .registerClientBoundPacket(0x05, AcknowledgeBlockChange::class, AcknowledgeBlockChange)
                    .registerClientBoundPacket(0x06, SetBlockDestroyStage::class, SetBlockDestroyStage)
                    .registerClientBoundPacket(0x07, BlockEntityData::class, BlockEntityData)
                    .registerClientBoundPacket(0x08, BlockAction::class, BlockAction)
                    .registerClientBoundPacket(0x0A, BossBar::class, BossBar)
                    .registerClientBoundPacket(0x25, Login::class, Login)

            )
}