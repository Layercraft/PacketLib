package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Particle | 0x22 | play | clientbound
 *
 * @property particleId particleId
 * @property longDistance longDistance
 * @property x x
 * @property y y
 * @property z z
 * @property offsetX offsetX
 * @property offsetY offsetY
 * @property offsetZ offsetZ
 * @property particleData particleData
 * @property particles particles
 * @see <a href="https://wiki.vg/Protocol#Particle_2">https://wiki.vg/Protocol#Particle_2</a>
 */

@MinecraftPacket(packetId = 0x22, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class WorldParticlesPacket(
    val particleId: Int,
    val longDistance: Boolean,
    val x: Float,
    val y: Float,
    val z: Float,
    val offsetX: Float,
    val offsetY: Float,
    val offsetZ: Float,
    val particleData: Float,
    val particles: Int,
) : ClientBoundPacket {

    companion object : PacketSerializer<WorldParticlesPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): WorldParticlesPacket {
            val particleId = input.readInt()
            val longDistance = input.readBoolean()
            val x = input.readFloat()
            val y = input.readFloat()
            val z = input.readFloat()
            val offsetX = input.readFloat()
            val offsetY = input.readFloat()
            val offsetZ = input.readFloat()
            val particleData = input.readFloat()
            val particles = input.readInt()

            return WorldParticlesPacket(particleId, longDistance, x, y, z, offsetX, offsetY, offsetZ, particleData, particles)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: WorldParticlesPacket) {
            output.writeInt(value.particleId)
            output.writeBoolean(value.longDistance)
            output.writeFloat(value.x)
            output.writeFloat(value.y)
            output.writeFloat(value.z)
            output.writeFloat(value.offsetX)
            output.writeFloat(value.offsetY)
            output.writeFloat(value.offsetZ)
            output.writeFloat(value.particleData)
            output.writeInt(value.particles)
        }
    }
}
