package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x1c | play | clientbound
 *
 * @property x x
 * @property y y
 * @property z z
 * @property radius radius
 * @property playerMotionX playerMotionX
 * @property playerMotionY playerMotionY
 * @property playerMotionZ playerMotionZ
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x1c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ExplosionPacket(
    val x: Float,
    val y: Float,
    val z: Float,
    val radius: Float,
    val playerMotionX: Float,
    val playerMotionY: Float,
    val playerMotionZ: Float,
) : ClientBoundPacket {

    companion object : PacketSerializer<ExplosionPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ExplosionPacket {
            val x = input.readFloat()
            val y = input.readFloat()
            val z = input.readFloat()
            val radius = input.readFloat()
            val playerMotionX = input.readFloat()
            val playerMotionY = input.readFloat()
            val playerMotionZ = input.readFloat()

            return ExplosionPacket(x, y, z, radius, playerMotionX, playerMotionY, playerMotionZ)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ExplosionPacket) {
            output.writeFloat(value.x)
            output.writeFloat(value.y)
            output.writeFloat(value.z)
            output.writeFloat(value.radius)
            output.writeFloat(value.playerMotionX)
            output.writeFloat(value.playerMotionY)
            output.writeFloat(value.playerMotionZ)
        }
    }
}
