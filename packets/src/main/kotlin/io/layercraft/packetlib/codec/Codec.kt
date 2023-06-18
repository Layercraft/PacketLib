package io.layercraft.packetlib.codec

import io.layercraft.packetlib.packets.ClientBoundPacket
import io.layercraft.packetlib.packets.Packet
import io.layercraft.packetlib.packets.PacketSerializer
import io.layercraft.packetlib.packets.ServerBoundPacket
import java.util.*
import kotlin.reflect.KClass

class Codec internal constructor(
    val protocolVersion: ProtocolVersion,
    internal val packets: EnumMap<PacketState, CodecRegistry> = EnumMap(PacketState::class.java),
) {

    private val list: List<CodecPacket<out Packet>> by lazy {
        packets.values.flatMap { it.clientPacketMap.values }.toList() + packets.values.flatMap { it.serverPacketMap.values }.toList()
    }

    fun getCodecPacket(packetDirection: PacketDirection, packetState: PacketState, packetId: Int): CodecPacket<*>? {
        return when (packetDirection) {
            PacketDirection.CLIENTBOUND -> packets[packetState]?.serverPacketMap?.get(packetId)
            PacketDirection.SERVERBOUND -> packets[packetState]?.clientPacketMap?.get(packetId)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Packet> getCodecPacketFromPacket(packet: T): CodecPacket<T>? {
        return list.find { it.packet == packet::class } as CodecPacket<T>?
    }

    fun packets(state: PacketState, registry: CodecRegistry.() -> Unit) {
        packets[state] = CodecRegistry().apply(registry)
    }
}

class CodecRegistry internal constructor(
    val clientPacketMap: HashMap<Int, CodecPacket<out ServerBoundPacket>> = HashMap(),
    val serverPacketMap: HashMap<Int, CodecPacket<out ClientBoundPacket>> = HashMap(),
) {
    fun <T : ClientBoundPacket> clientBound(packetId: Int, packet: KClass<T>, packetSerializer: PacketSerializer<T>) {
        serverPacketMap[packetId] = CodecPacket(packetId, packet, packetSerializer)
    }

    fun <T : ServerBoundPacket> serverBound(packetId: Int, packet: KClass<T>, packetSerializer: PacketSerializer<T>) {
        clientPacketMap[packetId] = CodecPacket(packetId, packet, packetSerializer)
    }
}

fun codec(protocolVersion: ProtocolVersion, init: Codec.() -> Unit): Codec {
    return Codec(protocolVersion).apply(init)
}

fun codecCopy(protocolVersion: ProtocolVersion, codec: Codec): Codec {
    return Codec(protocolVersion, codec.packets.clone())
}

data class CodecPacket<T : Packet> internal constructor(
    val packetId: Int,
    val packet: KClass<T>,
    val packetSerializer: PacketSerializer<T>,
)

enum class PacketDirection {
    SERVERBOUND, CLIENTBOUND,
}

enum class PacketState {
    HANDSHAKING, STATUS, LOGIN, PLAY,
}