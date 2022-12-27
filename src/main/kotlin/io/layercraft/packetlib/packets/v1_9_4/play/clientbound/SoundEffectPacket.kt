package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Sound Effect | 0x46 | play | clientbound
 *
 * @property soundId soundId
 * @property soundCategory soundCategory
 * @property x x
 * @property y y
 * @property z z
 * @property volume volume
 * @property pitch pitch
 * @see <a href="https://wiki.vg/Protocol#Sound_Effect">https://wiki.vg/Protocol#Sound_Effect</a>
 */

@MinecraftPacket(id = 0x46, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SoundEffectPacket(
    val soundId: Int, // varint
    val soundCategory: Int, // varint
    val x: Int,
    val y: Int,
    val z: Int,
    val volume: Float,
    val pitch: UByte,
) : ClientBoundPacket {

    companion object : PacketSerializer<SoundEffectPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SoundEffectPacket {
            val soundId = input.readVarInt()
            val soundCategory = input.readVarInt()
            val x = input.readInt()
            val y = input.readInt()
            val z = input.readInt()
            val volume = input.readFloat()
            val pitch = input.readUByte()

            return SoundEffectPacket(soundId, soundCategory, x, y, z, volume, pitch)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SoundEffectPacket) {
            output.writeVarInt(value.soundId)
            output.writeVarInt(value.soundCategory)
            output.writeInt(value.x)
            output.writeInt(value.y)
            output.writeInt(value.z)
            output.writeFloat(value.volume)
            output.writeUByte(value.pitch)
        }
    }
}
