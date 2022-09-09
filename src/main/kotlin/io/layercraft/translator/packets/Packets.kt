package io.layercraft.translator.packets

import io.layercraft.translator.packets.client.handshake.Handshake
import io.layercraft.translator.packets.client.handshake.LegacyServerListPing
import io.layercraft.translator.packets.client.login.EncryptionResponse
import io.layercraft.translator.packets.client.login.LoginPluginResponse
import io.layercraft.translator.packets.client.login.LoginStart
import io.layercraft.translator.packets.client.status.PingRequest
import io.layercraft.translator.packets.client.status.StatusRequest
import io.layercraft.translator.packets.server.login.*
import io.layercraft.translator.packets.server.status.PingResponse
import io.layercraft.translator.packets.server.status.StatusResponse
import kotlin.reflect.KClass

enum class Packets(
    val client: List<PacketType<out ClientPacket>>,
    val server: List<PacketType<out ServerPacket>>
) {
    HANDSHAKE(
        client = listOf(
            PacketType(0x00, Handshake::class, Handshake),
            PacketType(0xFE, LegacyServerListPing::class, LegacyServerListPing)
        ),
        server = emptyList()
    ),
    STATUS(
        client = listOf(
            PacketType(0x00, StatusRequest::class, StatusRequest),
            PacketType(0x01, PingRequest::class, PingRequest)
        ),
        server = listOf(
            PacketType(0x00, StatusResponse::class, StatusResponse),
            PacketType(0x01, PingResponse::class, PingResponse)
        )
    ),
    LOGIN(
        client = listOf(
            PacketType(0x00, LoginStart::class, LoginStart),
            PacketType(0x01, EncryptionResponse::class, EncryptionResponse),
            PacketType(0x02, LoginPluginResponse::class, LoginPluginResponse)

        ),
        server = listOf(
            PacketType(0x00, Disconnect::class, Disconnect),
            PacketType(0x01, EncryptionRequest::class, EncryptionRequest),
            PacketType(0x02, LoginSuccess::class, LoginSuccess),
            PacketType(0x03, SetCompression::class, SetCompression),
            PacketType(0x04, LoginPluginRequest::class, LoginPluginRequest),
        )
    ),
    PLAY(
        client = listOf(

        ),
        server = listOf(

        )
    );

    companion object {
        fun getFromId(id: Int, state: PacketState, direction: PacketDirection): PacketType<out Packet>? {
            return values()
                .firstOrNull {
                    when (it) {
                        HANDSHAKE -> state == PacketState.HANDSHAKE
                        STATUS -> state == PacketState.STATUS
                        LOGIN -> state == PacketState.LOGIN
                        PLAY -> state == PacketState.PLAY
                    }
                }?.let { packet ->
                    when (direction) {
                        PacketDirection.SERVERBOUND -> packet.client.firstOrNull { it.id == id }
                        PacketDirection.CLIENTBOUND -> packet.server.firstOrNull { it.id == id }
                    }
                }
        }

        fun getFromClass(clazz: KClass<out Packet>): PacketType<out Packet>? {
            return getAll().firstOrNull { it.kclass == clazz }
        }

        fun getAll(): Array<PacketType<*>> {
            return values().flatMap { it.client + it.server }.toTypedArray()
        }
    }
}

data class PacketType<T : Packet>(
    val id: Int,
    val kclass: KClass<T>,
    val serializer: PacketSerializer<T>
)
