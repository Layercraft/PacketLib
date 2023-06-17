package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer
import java.util.UUID
/**
 * Spawn Player | 0x02 | play | clientbound
 *
 * @param entityId entityId
 * @param playerUUID playerUUID
 * @param x x
 * @param y y
 * @param z z
 * @param yaw yaw
 * @param pitch pitch
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Spawn_Player">https://wiki.vg/Protocol#Spawn_Player</a>
 */

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
        override fun deserialize(input: MCProtocolDeserializer<*>): NamedEntitySpawnPacket {
            val entityId = input.readVarInt()
            val playerUUID = input.readUUID()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val yaw = input.readByte()
            val pitch = input.readByte()

            return NamedEntitySpawnPacket(entityId, playerUUID, x, y, z, yaw, pitch)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: NamedEntitySpawnPacket) {
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