package io.layercraft.translator.codec

import io.layercraft.translator.data.ProtocolVersion
import io.layercraft.translator.packets.*
import java.util.*
import kotlin.reflect.KClass

class MinecraftCodec private constructor(
    val protocolVersion: ProtocolVersion,
    val packets: EnumMap<PacketState, MinecraftCodecRegistry> = EnumMap(PacketState::class.java)
) {
    val packetVersionAsInt: Int
        get() = protocolVersion.v

    val packetVersionAsString: String
        get() = protocolVersion.name

    fun registerPacketRegistry(state: PacketState, registry: MinecraftCodecRegistry) = apply {
        packets[state] = registry
    }

    fun getClientBoundPacket(packetState: PacketState, packetId: Int): MinecraftServerCodecPacket? {
        return packets[packetState]?.serverPacketMap?.get(packetId)
    }

    fun getServerBoundPacket(packetState: PacketState, packetId: Int): MinecraftClientCodecPacket? {
        return packets[packetState]?.clientPacketMap?.get(packetId)
    }



    companion object {
        fun create(protocolVersion: ProtocolVersion): MinecraftCodec = MinecraftCodec(protocolVersion)
    }
}

class MinecraftCodecRegistry private constructor(
    val clientPacketMap: HashMap<Int, MinecraftClientCodecPacket> = HashMap(),
    val serverPacketMap: HashMap<Int, MinecraftServerCodecPacket> = HashMap()
) {
    fun registerClientBoundPacket(packetId: Int, packet: KClass<out ClientBoundPacket>, packetSerializer: PacketSerializer<out ClientBoundPacket>) =
        apply {
            serverPacketMap[packetId] = MinecraftServerCodecPacket(packetId, packet, packetSerializer)
        }

    fun registerServerBoundPacket(packetId: Int, packet: KClass<out ServerBoundPacket>, packetSerializer: PacketSerializer<out ServerBoundPacket>) =
        apply {
            clientPacketMap[packetId] = MinecraftClientCodecPacket(packetId, packet, packetSerializer)
        }

    companion object {
        fun create(): MinecraftCodecRegistry = MinecraftCodecRegistry()
    }
}

interface MinecraftCodecPacket {
    val packetId: Int
    val packet: KClass<out Packet>
    val packetSerializer: PacketSerializer<out Packet>
}

data class MinecraftServerCodecPacket(
    override val packetId: Int,
    override val packet: KClass<out ClientBoundPacket>,
    override val packetSerializer: PacketSerializer<out ClientBoundPacket>
) : MinecraftCodecPacket

data class MinecraftClientCodecPacket(
    override val packetId: Int,
    override val packet: KClass<out ServerBoundPacket>,
    override val packetSerializer: PacketSerializer<out ServerBoundPacket>
): MinecraftCodecPacket
