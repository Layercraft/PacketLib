package io.layercraft.packetlib.serialization.types

import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import io.layercraft.packetlib.types.ChunkBlockEntity

object ChunkBlockEntitySerializer {
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