package io.layercraft.packetlib.generator.codegen

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.MemberName

/** References to the packetlib runtime the generated code compiles against. */
object Runtime {
    private const val BASE = "io.layercraft.packetlib"

    val PACKET = ClassName("$BASE.packets", "Packet")
    val CLIENT_BOUND_PACKET = ClassName("$BASE.packets", "ClientBoundPacket")
    val SERVER_BOUND_PACKET = ClassName("$BASE.packets", "ServerBoundPacket")
    val PACKET_SERIALIZER = ClassName("$BASE.packets", "PacketSerializer")

    val DESERIALIZER = ClassName("$BASE.serialization", "MCProtocolDeserializer")
    val SERIALIZER = ClassName("$BASE.serialization", "MCProtocolSerializer")

    val NBT = ClassName("$BASE.types", "NBT")
    val POSITION = ClassName("$BASE.types", "Position")
    val CHUNK_BLOCK_ENTITY = ClassName("$BASE.types", "ChunkBlockEntity")

    val CODEC = ClassName("$BASE.codec", "Codec")
    val PROTOCOL_VERSION = ClassName("$BASE.codec", "ProtocolVersion")
    val PACKET_STATE = ClassName("$BASE.codec", "PacketState")
    val CODEC_FUN = MemberName("$BASE.codec", "codec")
    val CODEC_COPY_FUN = MemberName("$BASE.codec", "codecCopy")

    const val PACKETS_PACKAGE = "$BASE.packets"
    const val CODEC_PACKAGE = "$BASE.codec"
}
