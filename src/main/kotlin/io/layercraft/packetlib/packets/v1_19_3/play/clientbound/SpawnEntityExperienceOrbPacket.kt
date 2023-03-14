package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Spawn Experience Orb | 0x01 | play | clientbound
 *
 * @param entityId entityId
 * @param x x
 * @param y y
 * @param z z
 * @param count count
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Spawn_Experience_Orb">https://wiki.vg/Protocol#Spawn_Experience_Orb</a>
 */

@MinecraftPacket(id = 0x01, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SpawnEntityExperienceOrbPacket(
    val entityId: Int, // varint
    val x: Double,
    val y: Double,
    val z: Double,
    val count: Short,
) : ClientBoundPacket {
    companion object : PacketSerializer<SpawnEntityExperienceOrbPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SpawnEntityExperienceOrbPacket {
            val entityId = input.readVarInt()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val count = input.readShort()

            return SpawnEntityExperienceOrbPacket(entityId, x, y, z, count)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SpawnEntityExperienceOrbPacket) {
            output.writeVarInt(value.entityId)
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeShort(value.count)
        }
    }
}