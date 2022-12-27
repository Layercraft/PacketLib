package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x1b | play | clientbound
 *
 * @property x x
 * @property y y
 * @property z z
 * @property radius radius
 * @property playerMotionX playerMotionX
 * @property playerMotionY playerMotionY
 * @property playerMotionZ playerMotionZ
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x1b, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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