package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Explosion | 0x1b | play | clientbound
 *
 * @param x x
 * @param y y
 * @param z z
 * @param radius radius
 * @param affectedBlockOffsets list of ExplosionPacketAffectedBlockOffsets
 * @param playerMotionX playerMotionX
 * @param playerMotionY playerMotionY
 * @param playerMotionZ playerMotionZ
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Explosion">https://wiki.vg/Protocol#Explosion</a>
 */

@MinecraftPacket(id = 0x1b, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ExplosionPacket(
    val x: Float,
    val y: Float,
    val z: Float,
    val radius: Float,
    val affectedBlockOffsets: List<ExplosionPacketAffectedBlockOffsets>, // varint array
    val playerMotionX: Float,
    val playerMotionY: Float,
    val playerMotionZ: Float,
) : ClientBoundPacket {
    companion object : PacketSerializer<ExplosionPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ExplosionPacket {
            val x = input.readFloat()
            val y = input.readFloat()
            val z = input.readFloat()
            val radius = input.readFloat()
            val affectedBlockOffsets = input.readVarIntArray { arrayInput ->
                val x = arrayInput.readByte()
                val y = arrayInput.readByte()
                val z = arrayInput.readByte()

                return@readVarIntArray ExplosionPacketAffectedBlockOffsets(x, y, z)
            }
            val playerMotionX = input.readFloat()
            val playerMotionY = input.readFloat()
            val playerMotionZ = input.readFloat()

            return ExplosionPacket(x, y, z, radius, affectedBlockOffsets, playerMotionX, playerMotionY, playerMotionZ)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ExplosionPacket) {
            output.writeFloat(value.x)
            output.writeFloat(value.y)
            output.writeFloat(value.z)
            output.writeFloat(value.radius)

            output.writeVarIntArray(value.affectedBlockOffsets) { arrayValue, arrayOutput ->
                arrayOutput.writeByte(arrayValue.x)
                arrayOutput.writeByte(arrayValue.y)
                arrayOutput.writeByte(arrayValue.z)
            }

            output.writeFloat(value.playerMotionX)
            output.writeFloat(value.playerMotionY)
            output.writeFloat(value.playerMotionZ)
        }
    }
}

/**
 * ExplosionPacketAffectedBlockOffsets
 *
 * @param x x
 * @param y y
 * @param z z
*/
data class ExplosionPacketAffectedBlockOffsets(
    val x: Byte,
    val y: Byte,
    val z: Byte,
)