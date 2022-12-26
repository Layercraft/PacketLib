package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID

/**
 * Spawn Player | 0x02 | play | clientbound
 *
 * @property entityId entityId
 * @property playerUUID playerUUID
 * @property x x
 * @property y y
 * @property z z
 * @property yaw yaw
 * @property pitch pitch
 * @see <a href="https://wiki.vg/Protocol#Spawn_Player">https://wiki.vg/Protocol#Spawn_Player</a>
 */

@MinecraftPacket(packetId = 0x02, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class NamedEntitySpawnPacket(
    val entityId: Int, // varint
    val playerUUID: UUID,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Byte,
    val pitch: Byte,
) : ClientBoundPacket {

    companion object : PacketSerializer<NamedEntitySpawnPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): NamedEntitySpawnPacket {
            val entityId = input.readVarInt()
            val playerUUID = input.readUUID()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val yaw = input.readByte()
            val pitch = input.readByte()

            return NamedEntitySpawnPacket(entityId, playerUUID, x, y, z, yaw, pitch)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: NamedEntitySpawnPacket) {
            output.writeVarInt(value.entityId)
            output.writeUUID(value.playerUUID)
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeByte(value.yaw)
            output.writeByte(value.pitch)
        }
    }
}