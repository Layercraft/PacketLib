package io.layercraft.packetlib.types

import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

data class ChunkBlockEntity(
    val chunkXz: Byte,
    val y: Short,
    val type: Int,
    val data: NBT,
) {
    companion object {
        fun read(deserializer: MCProtocolDeserializer<*>): ChunkBlockEntity {
            val packedXZ = deserializer.readByte()
            val y = deserializer.readShort()
            val type = deserializer.readVarInt()
            val data = deserializer.readNbt()

            return ChunkBlockEntity(packedXZ, y, type, data)
        }

        fun write(entity: ChunkBlockEntity, serializeInterface: MCProtocolSerializer<*>) {
            serializeInterface.writeByte(entity.chunkXz)
            serializeInterface.writeShort(entity.y)
            serializeInterface.writeVarInt(entity.type)
            serializeInterface.writeNbt(entity.data)
        }
    }
}