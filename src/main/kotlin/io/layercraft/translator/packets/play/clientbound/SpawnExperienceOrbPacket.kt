package io.layercraft.translator.packets.play.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Spawn experience orb | 0x01 | play | client-bound
 *
 * Spawns one or more experience orbs.
 * @property entityId
 * @property x
 * @property y
 * @property z
 * @property count The amount of experience this orb will reward once collected.
 * @see <a href="https://wiki.vg/Protocol#Spawn_Experience_Orb">https://wiki.vg/Protocol#Spawn_Experience_Orb</a>
 */
@MinecraftPacket(0x01, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class SpawnExperienceOrbPacket(
    val entityId: Int, // varint
    val x: Double,
    val y: Double,
    val z: Double,
    val count: Short,
) : ClientBoundPacket {
    companion object : PacketSerializer<SpawnExperienceOrbPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SpawnExperienceOrbPacket {
            val entityId = input.readVarInt()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val count = input.readShort()

            return SpawnExperienceOrbPacket(entityId, x, y, z, count)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SpawnExperienceOrbPacket) {
            output.writeVarInt(value.entityId)
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeShort(value.count)
        }
    }
}