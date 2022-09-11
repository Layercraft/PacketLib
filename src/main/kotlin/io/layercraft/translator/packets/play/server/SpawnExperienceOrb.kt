package io.layercraft.translator.packets.play.server

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc

/**
 * Spawn experience orb | 0x01 | play | client-bound
 *
 * Spawns one or more experience orbs.
 * @property entityId VarInt -
 * @property x Double -
 * @property y Double -
 * @property z Double -
 * @property count Short - The amount of experience this orb will reward once collected.
 * @see <a href="https://wiki.vg/Protocol#Spawn_Experience_Orb">https://wiki.vg/Protocol#Spawn_Experience_Orb</a>
 */
@MinecraftPacket(0x01, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class SpawnExperienceOrb(
    val entityId: Int,
    val x: Double,
    val y: Double,
    val z: Double,
    val count: Short
) : ClientBoundPacket {
    companion object: PacketSerializer<SpawnExperienceOrb> {
        override fun serialize(input: Input): SpawnExperienceOrb {
            val entityId = input.mc.readVarInt()
            val x = input.mc.readDouble()
            val y = input.mc.readDouble()
            val z = input.mc.readDouble()
            val count = input.mc.readShort()

            return SpawnExperienceOrb(entityId, x, y, z, count)
        }

        override fun deserialize(output: Output, value: SpawnExperienceOrb) {
            output.mc.writeVarInt(value.entityId)
            output.mc.writeDouble(value.x)
            output.mc.writeDouble(value.y)
            output.mc.writeDouble(value.z)
            output.mc.writeShort(value.count)
        }
    }
}
