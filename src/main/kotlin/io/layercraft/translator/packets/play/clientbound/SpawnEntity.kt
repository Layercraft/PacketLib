package io.layercraft.translator.packets.play.clientbound

import io.layercraft.translator.data.entity.EntityType
import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID

/**
 * Spawn entity | 0x00 | play | client-bound
 *
 * Sent by the server when a vehicle or other non-living entity is created.
 * @property entityId A unique integer ID mostly used in the protocol to identify the entity.
 * @property uuid A unique identifier that is mostly used in persistence and places where the uniqueness matters more.
 * @property type The type of the entity (see "type" field of the list of Mob types).
 * @property x
 * @property y
 * @property z
 * @property pitch To get the real pitch, you must divide this by (256.0F / 360.0F)
 * @property yaw To get the real pitch, you must divide this by (256.0F / 360.0F)
 * @property headYaw
 * @property data Meaning dependent on the value of the Type field, see Object Data for details.
 * @property velocityX
 * @property velocityY
 * @property velocityZ
 * @see <a href="https://wiki.vg/Protocol#Spawn_Entity">https://wiki.vg/Protocol#Spawn_Entity</a>
 */
@MinecraftPacket(0x00, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class SpawnEntity(
    val entityId: Int, // varint
    val uuid: UUID,
    val type: EntityType, // varint
    val x: Double,
    val y: Double,
    val z: Double,
    val pitch: Float, // angle
    val yaw: Float, // angle
    val headYaw: Float,
    val data: Int, // varint
    val velocityX: Short,
    val velocityY: Short,
    val velocityZ: Short,
) : ClientBoundPacket {
    companion object : PacketSerializer<SpawnEntity> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SpawnEntity {
            val entityId = input.readVarInt()
            val uuid = input.readUUID()
            val type = EntityType.byType(input.readVarInt()) ?: throw IllegalArgumentException("Invalid entity type")
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val pitch = input.readAngle()
            val yaw = input.readAngle()
            val headYaw = input.readAngle()
            val data = input.readVarInt()
            val velocityX = input.readShort()
            val velocityY = input.readShort()
            val velocityZ = input.readShort()

            return SpawnEntity(entityId, uuid, type, x, y, z, pitch, yaw, headYaw, data, velocityX, velocityY, velocityZ)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SpawnEntity) {
            output.writeVarInt(value.entityId)
            output.writeUUID(value.uuid)
            output.writeVarInt(value.type.type)
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeAngle(value.pitch)
            output.writeAngle(value.yaw)
            output.writeAngle(value.headYaw)
            output.writeVarInt(value.data)
            output.writeShort(value.velocityX)
            output.writeShort(value.velocityY)
            output.writeShort(value.velocityZ)
        }
    }
}