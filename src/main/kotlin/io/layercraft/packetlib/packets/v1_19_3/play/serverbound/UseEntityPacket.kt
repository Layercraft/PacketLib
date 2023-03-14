package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Interact | 0x0f | play | serverbound
 *
 * @param target target
 * @param mouse mouse
 * @param x x
 * @param y y
 * @param z z
 * @param hand hand
 * @param sneaking sneaking
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Interact">https://wiki.vg/Protocol#Interact</a>
 */

@MinecraftPacket(id = 0x0f, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class UseEntityPacket(
    val target: Int, // varint
    val mouse: Int, // varint
    val x: Float?,
    val y: Float?,
    val z: Float?,
    val hand: Int?, // varint
    val sneaking: Boolean,
) : ServerBoundPacket {
    companion object : PacketSerializer<UseEntityPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): UseEntityPacket {
            val target = input.readVarInt()
            val mouse = input.readVarInt()
            val x = when (mouse) {
                2 -> input.readFloat()
                else -> null
            }
            val y = when (mouse) {
                2 -> input.readFloat()
                else -> null
            }
            val z = when (mouse) {
                2 -> input.readFloat()
                else -> null
            }
            val hand = when (mouse) {
                0 -> input.readVarInt()
                2 -> input.readVarInt()
                else -> null
            }
            val sneaking = input.readBoolean()

            return UseEntityPacket(target, mouse, x, y, z, hand, sneaking)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: UseEntityPacket) {
            output.writeVarInt(value.target)
            output.writeVarInt(value.mouse)
            when (value.mouse) {
                2 -> output.writeFloat(value.x!!)
                else -> {}
            }
            when (value.mouse) {
                2 -> output.writeFloat(value.y!!)
                else -> {}
            }
            when (value.mouse) {
                2 -> output.writeFloat(value.z!!)
                else -> {}
            }
            when (value.mouse) {
                0 -> output.writeVarInt(value.hand!!)
                2 -> output.writeVarInt(value.hand!!)
                else -> {}
            }
            output.writeBoolean(value.sneaking)
        }
    }
}