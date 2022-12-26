package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x5d | play | clientbound
 *
 * @property soundId soundId
 * @property soundCategory soundCategory
 * @property x x
 * @property y y
 * @property z z
 * @property volume volume
 * @property pitch pitch
 * @property seed seed
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x5d, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SoundEffectPacket {
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

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SoundEffectPacket) {
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