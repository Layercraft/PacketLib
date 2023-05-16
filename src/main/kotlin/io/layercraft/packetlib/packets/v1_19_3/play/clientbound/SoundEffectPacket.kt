package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Sound Effect | 0x5e | play | clientbound
 *
 * @param soundId soundId
 * @param soundCategory soundCategory
 * @param x x
 * @param y y
 * @param z z
 * @param volume volume
 * @param pitch pitch
 * @param seed seed
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18071#Sound_Effect">https://wiki.vg/Protocol#Sound_Effect</a>
 */

@MinecraftPacket(id = 0x5e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SoundEffectPacket(
    val soundId: Int, // varint
    val soundCategory: Int, // varint
    val x: Int,
    val y: Int,
    val z: Int,
    val volume: Float,
    val pitch: Float,
    val seed: Long,
) : ClientBoundPacket {
    companion object : PacketSerializer<SoundEffectPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SoundEffectPacket {
            val soundId = input.readVarInt()
            val soundCategory = input.readVarInt()
            val x = input.readInt()
            val y = input.readInt()
            val z = input.readInt()
            val volume = input.readFloat()
            val pitch = input.readFloat()
            val seed = input.readLong()

            return SoundEffectPacket(soundId, soundCategory, x, y, z, volume, pitch, seed)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SoundEffectPacket) {
            output.writeVarInt(value.soundId)
            output.writeVarInt(value.soundCategory)
            output.writeInt(value.x)
            output.writeInt(value.y)
            output.writeInt(value.z)
            output.writeFloat(value.volume)
            output.writeFloat(value.pitch)
            output.writeLong(value.seed)
        }
    }
}