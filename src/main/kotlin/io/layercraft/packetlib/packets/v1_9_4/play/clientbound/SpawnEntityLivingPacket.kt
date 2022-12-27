package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID

/**
 * Spawn Mob | 0x03 | play | clientbound
 *
 * @property entityId entityId
 * @property entityUUID entityUUID
 * @property type type
 * @property x x
 * @property y y
 * @property z z
 * @property yaw yaw
 * @property pitch pitch
 * @property headPitch headPitch
 * @property velocityX velocityX
 * @property velocityY velocityY
 * @property velocityZ velocityZ
 * @see <a href="https://wiki.vg/Protocol#Spawn_Mob">https://wiki.vg/Protocol#Spawn_Mob</a>
 */

@MinecraftPacket(id = 0x03, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SpawnEntityLivingPacket(
    val entityId: Int, // varint
    val entityUUID: UUID,
    val type: UByte,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Byte,
    val pitch: Byte,
    val headPitch: Byte,
    val velocityX: Short,
    val velocityY: Short,
    val velocityZ: Short,
) : ClientBoundPacket {

    companion object : PacketSerializer<SpawnEntityLivingPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SpawnEntityLivingPacket {
            val entityId = input.readVarInt()
            val entityUUID = input.readUUID()
            val type = input.readUByte()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val yaw = input.readByte()
            val pitch = input.readByte()
            val headPitch = input.readByte()
            val velocityX = input.readShort()
            val velocityY = input.readShort()
            val velocityZ = input.readShort()

            return SpawnEntityLivingPacket(entityId, entityUUID, type, x, y, z, yaw, pitch, headPitch, velocityX, velocityY, velocityZ)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SpawnEntityLivingPacket) {
            output.writeVarInt(value.entityId)
            output.writeUUID(value.entityUUID)
            output.writeUByte(value.type)
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeByte(value.yaw)
            output.writeByte(value.pitch)
            output.writeByte(value.headPitch)
            output.writeShort(value.velocityX)
            output.writeShort(value.velocityY)
            output.writeShort(value.velocityZ)
        }
    }
}
