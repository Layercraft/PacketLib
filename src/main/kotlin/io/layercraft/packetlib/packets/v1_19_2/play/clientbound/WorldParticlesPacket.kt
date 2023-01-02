package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Particle | 0x23 | play | clientbound
 *
 * @param particleId particleId
 * @param longDistance longDistance
 * @param x x
 * @param y y
 * @param z z
 * @param offsetX offsetX
 * @param offsetY offsetY
 * @param offsetZ offsetZ
 * @param particleData particleData
 * @param particles particles
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Particle_2">https://wiki.vg/Protocol#Particle_2</a>
 */

@MinecraftPacket(id = 0x23, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class WorldParticlesPacket(
    val particleId: Int, // varint
    val longDistance: Boolean,
    val x: Double,
    val y: Double,
    val z: Double,
    val offsetX: Float,
    val offsetY: Float,
    val offsetZ: Float,
    val particleData: Float,
    val particles: Int,
) : ClientBoundPacket {
    companion object : PacketSerializer<WorldParticlesPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): WorldParticlesPacket {
            val particleId = input.readVarInt()
            val longDistance = input.readBoolean()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val offsetX = input.readFloat()
            val offsetY = input.readFloat()
            val offsetZ = input.readFloat()
            val particleData = input.readFloat()
            val particles = input.readInt()

            return WorldParticlesPacket(particleId, longDistance, x, y, z, offsetX, offsetY, offsetZ, particleData, particles)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: WorldParticlesPacket) {
            output.writeVarInt(value.particleId)
            output.writeBoolean(value.longDistance)
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeFloat(value.offsetX)
            output.writeFloat(value.offsetY)
            output.writeFloat(value.offsetZ)
            output.writeFloat(value.particleData)
            output.writeInt(value.particles)
        }
    }
}