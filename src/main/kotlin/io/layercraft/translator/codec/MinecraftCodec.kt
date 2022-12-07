package io.layercraft.translator.codec

import io.layercraft.translator.data.ProtocolVersion
import io.layercraft.translator.packets.*
import java.util.EnumMap
import kotlin.reflect.KClass
class MinecraftCodec private constructor(
    val protocolVersion: ProtocolVersion,
    val packets: EnumMap<PacketState, MinecraftCodecRegistry> = EnumMap(PacketState::class.java)
) {
    val protocolVersionAsInt: Int
        get() = protocolVersion.v

    val protocolVersionAsString: String
        get() = protocolVersion.name

    private val list: List<MinecraftCodecPacket<out Packet>>
        get() = packets.values.flatMap { it.clientPacketMap.values }.toList() + packets.values.flatMap { it.serverPacketMap.values }.toList()

    fun registerPacketRegistry(state: PacketState, registry: MinecraftCodecRegistry) = apply {
        packets[state] = registry
    }

    fun getClientBoundCodecPacket(packetState: PacketState, packetId: Int): MinecraftServerCodecPacket<*>? {
        return packets[packetState]?.serverPacketMap?.get(packetId)
    }

    fun getServerBoundCodecPacket(packetState: PacketState, packetId: Int): MinecraftClientCodecPacket<*>? {
        return packets[packetState]?.clientPacketMap?.get(packetId)
    }

    fun getCodecPacket(packetDirection: PacketDirection, packetState: PacketState, packetId: Int): MinecraftCodecPacket<*>? {
        return when (packetDirection) {
            PacketDirection.CLIENTBOUND -> getClientBoundCodecPacket(packetState, packetId)
            PacketDirection.SERVERBOUND -> getServerBoundCodecPacket(packetState, packetId)
        }
    }

    fun <T : Packet> getCodecPacketFromPacket(packet: T): MinecraftCodecPacket<T>? {
        return list.find { it.packet == packet::class } as MinecraftCodecPacket<T>?
    }

    companion object {
        fun create(protocolVersion: ProtocolVersion): MinecraftCodec = MinecraftCodec(protocolVersion)
    }
}

class MinecraftCodecRegistry private constructor(
    val clientPacketMap: HashMap<Int, MinecraftClientCodecPacket<out ServerBoundPacket>> = HashMap(),
    val serverPacketMap: HashMap<Int, MinecraftServerCodecPacket<out ClientBoundPacket>> = HashMap()
) {
    fun <T : ClientBoundPacket> registerClientBoundPacket(packetId: Int, packet: KClass<T>, packetSerializer: PacketSerializer<T>) =
        apply {
            serverPacketMap[packetId] = MinecraftServerCodecPacket(packetId, packet, packetSerializer)
        }

    fun <T : ServerBoundPacket> registerServerBoundPacket(packetId: Int, packet: KClass<T>, packetSerializer: PacketSerializer<T>) =
        apply {
            clientPacketMap[packetId] = MinecraftClientCodecPacket(packetId, packet, packetSerializer)
        }

    companion object {
        fun create(): MinecraftCodecRegistry = MinecraftCodecRegistry()
    }
}

interface MinecraftCodecPacket<T : Packet> {
    val packetId: Int
    val packet: KClass<T>
    val packetSerializer: PacketSerializer<T>
}

data class MinecraftServerCodecPacket<T : ClientBoundPacket>(
    override val packetId: Int,
    override val packet: KClass<T>,
    override val packetSerializer: PacketSerializer<T>
) : MinecraftCodecPacket<T>

data class MinecraftClientCodecPacket<T : ServerBoundPacket>(
    override val packetId: Int,
    override val packet: KClass<T>,
    override val packetSerializer: PacketSerializer<T>
) : MinecraftCodecPacket<T>