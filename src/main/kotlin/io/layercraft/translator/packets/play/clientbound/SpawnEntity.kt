package io.layercraft.translator.packets.play.clientbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.data.entity.EntityType
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc
import java.util.*

/**
 * Spawn entity | 0x00 | play | client-bound
 *
 * Sent by the server when a vehicle or other non-living entity is created.
 * @property entityId Varint - A unique integer ID mostly used in the protocol to identify the entity.
 * @property uuid UUID - A unique identifier that is mostly used in persistence and places where the uniqueness matters more.
 * @property type VarInt - The type of the entity (see "type" field of the list of Mob types).
 * @property x
 * @property y
 * @property z
 * @property pitch Angle - To get the real pitch, you must divide this by (256.0F / 360.0F)
 * @property yaw Angele - To get the real pitch, you must divide this by (256.0F / 360.0F)
 * @property data Varint - Meaning dependent on the value of the Type field, see Object Data for details.
 * @property velocityX
 * @property velocityY
 * @property velocityZ
 * @see <a href="https://wiki.vg/Protocol#Spawn_Entity">https://wiki.vg/Protocol#Spawn_Entity</a>
 */
@MinecraftPacket(0x00, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class SpawnEntity(
    val entityId: Int,
    val uuid: UUID,
    val type: EntityType,
    val x: Double,
    val y: Double,
    val z: Double,
    val pitch: Float,
    val yaw: Float,
    val headYaw: Float,
    val data: Int,
    val velocityX: Short,
    val velocityY: Short,
    val velocityZ: Short
) : ClientBoundPacket {
    companion object : PacketSerializer<SpawnEntity> {
        override fun serialize(input: Input): SpawnEntity {
            val entityId = input.mc.readVarInt()
            val uuid = input.mc.readUUID()
            val type = EntityType.byType(input.mc.readVarInt()) ?: throw IllegalArgumentException("Invalid entity type")
            val x = input.mc.readDouble()
            val y = input.mc.readDouble()
            val z = input.mc.readDouble()
            val pitch = input.mc.readAngle()
            val yaw = input.mc.readAngle()
            val headYaw = input.mc.readAngle()
            val data = input.mc.readVarInt()
            val velocityX = input.mc.readShort()
            val velocityY = input.mc.readShort()
            val velocityZ = input.mc.readShort()

            return SpawnEntity(entityId, uuid, type, x, y, z, pitch, yaw, headYaw, data, velocityX, velocityY, velocityZ)
        }

        override fun deserialize(output: Output, value: SpawnEntity) {
            output.mc.writeVarInt(value.entityId)
            output.mc.writeUUID(value.uuid)
            output.mc.writeVarInt(value.type.type)
            output.mc.writeDouble(value.x)
            output.mc.writeDouble(value.y)
            output.mc.writeDouble(value.z)
            output.mc.writeAngle(value.pitch)
            output.mc.writeAngle(value.yaw)
            output.mc.writeAngle(value.headYaw)
            output.mc.writeVarInt(value.data)
            output.mc.writeShort(value.velocityX)
            output.mc.writeShort(value.velocityY)
            output.mc.writeShort(value.velocityZ)
        }

    }
}
