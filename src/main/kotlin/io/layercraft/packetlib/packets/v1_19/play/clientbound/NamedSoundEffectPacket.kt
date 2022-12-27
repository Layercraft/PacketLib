package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Custom Sound Effect | 0x16 | play | clientbound
 *
 * @property soundName soundName
 * @property soundCategory soundCategory
 * @property x x
 * @property y y
 * @property z z
 * @property volume volume
 * @property pitch pitch
 * @property seed seed
 * @see <a href="https://wiki.vg/Protocol#Custom_Sound_Effect">https://wiki.vg/Protocol#Custom_Sound_Effect</a>
 */

@MinecraftPacket(id = 0x16, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class NamedSoundEffectPacket(
    val soundName: String,
    val soundCategory: Int, // varint
    val x: Int,
    val y: Int,
    val z: Int,
    val volume: Float,
    val pitch: Float,
    val seed: Long,
) : ClientBoundPacket {

    companion object : PacketSerializer<NamedSoundEffectPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): NamedSoundEffectPacket {
            val soundName = input.readString()
            val soundCategory = input.readVarInt()
            val x = input.readInt()
            val y = input.readInt()
            val z = input.readInt()
            val volume = input.readFloat()
            val pitch = input.readFloat()
            val seed = input.readLong()

            return NamedSoundEffectPacket(soundName, soundCategory, x, y, z, volume, pitch, seed)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: NamedSoundEffectPacket) {
            output.writeString(value.soundName)
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