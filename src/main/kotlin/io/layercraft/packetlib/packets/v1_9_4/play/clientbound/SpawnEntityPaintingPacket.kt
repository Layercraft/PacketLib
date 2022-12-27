package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
import io.layercraft.packetlib.types.Position

/**
 * Spawn Painting | 0x04 | play | clientbound
 *
 * @property entityId entityId
 * @property entityUUID entityUUID
 * @property title title
 * @property location location
 * @property direction direction
 * @see <a href="https://wiki.vg/Protocol#Spawn_Painting">https://wiki.vg/Protocol#Spawn_Painting</a>
 */

@MinecraftPacket(id = 0x04, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SpawnEntityPaintingPacket(
    val entityId: Int, // varint
    val entityUUID: UUID,
    val title: String,
    val location: Position,
    val direction: UByte,
) : ClientBoundPacket {

    companion object : PacketSerializer<SpawnEntityPaintingPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SpawnEntityPaintingPacket {
            val entityId = input.readVarInt()
            val entityUUID = input.readUUID()
            val title = input.readString()
            val location = input.readPosition()
            val direction = input.readUByte()

            return SpawnEntityPaintingPacket(entityId, entityUUID, title, location, direction)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SpawnEntityPaintingPacket) {
            output.writeVarInt(value.entityId)
            output.writeUUID(value.entityUUID)
            output.writeString(value.title)
            output.writePosition(value.location)
            output.writeUByte(value.direction)
        }
    }
}
