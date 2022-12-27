package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID

/**
 * Spawn Entity | 0x00 | play | clientbound
 *
 * @property entityId entityId
 * @property objectUUID objectUUID
 * @property type type
 * @property x x
 * @property y y
 * @property z z
 * @property pitch pitch
 * @property yaw yaw
 * @property headPitch headPitch
 * @property objectData objectData
 * @property velocityX velocityX
 * @property velocityY velocityY
 * @property velocityZ velocityZ
 * @see <a href="https://wiki.vg/Protocol#Spawn_Entity">https://wiki.vg/Protocol#Spawn_Entity</a>
 */

@MinecraftPacket(id = 0x00, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SpawnEntityPacket(
    val entityId: Int, // varint
    val objectUUID: UUID,
    val type: Int, // varint
    val x: Double,
    val y: Double,
    val z: Double,
    val pitch: Byte,
    val yaw: Byte,
    val headPitch: Byte,
    val objectData: Int, // varint
    val velocityX: Short,
    val velocityY: Short,
    val velocityZ: Short,
) : ClientBoundPacket {

    companion object : PacketSerializer<SpawnEntityPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SpawnEntityPacket {
            val entityId = input.readVarInt()
            val objectUUID = input.readUUID()
            val type = input.readVarInt()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val pitch = input.readByte()
            val yaw = input.readByte()
            val headPitch = input.readByte()
            val objectData = input.readVarInt()
            val velocityX = input.readShort()
            val velocityY = input.readShort()
            val velocityZ = input.readShort()

            return SpawnEntityPacket(entityId, objectUUID, type, x, y, z, pitch, yaw, headPitch, objectData, velocityX, velocityY, velocityZ)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SpawnEntityPacket) {
            output.writeVarInt(value.entityId)
            output.writeUUID(value.objectUUID)
            output.writeVarInt(value.type)
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeByte(value.pitch)
            output.writeByte(value.yaw)
            output.writeByte(value.headPitch)
            output.writeVarInt(value.objectData)
            output.writeShort(value.velocityX)
            output.writeShort(value.velocityY)
            output.writeShort(value.velocityZ)
        }
    }
}