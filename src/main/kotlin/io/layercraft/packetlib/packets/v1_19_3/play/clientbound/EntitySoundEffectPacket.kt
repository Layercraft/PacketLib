package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Entity Sound Effect | 0x5d | play | clientbound
 *
 * @param soundId soundId
 * @param soundCategory soundCategory
 * @param entityId entityId
 * @param volume volume
 * @param pitch pitch
 * @param seed seed
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Entity_Sound_Effect">https://wiki.vg/Protocol#Entity_Sound_Effect</a>
 */

data class EntitySoundEffectPacket(
    val soundId: Int, // varint
    val soundCategory: Int, // varint
    val entityId: Int, // varint
    val volume: Float,
    val pitch: Float,
    val seed: Long,
) : ClientBoundPacket {
    companion object : PacketSerializer<EntitySoundEffectPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): EntitySoundEffectPacket {
            val soundId = input.readVarInt()
            val soundCategory = input.readVarInt()
            val entityId = input.readVarInt()
            val volume = input.readFloat()
            val pitch = input.readFloat()
            val seed = input.readLong()

            return EntitySoundEffectPacket(soundId, soundCategory, entityId, volume, pitch, seed)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: EntitySoundEffectPacket) {
            output.writeVarInt(value.soundId)
            output.writeVarInt(value.soundCategory)
            output.writeVarInt(value.entityId)
            output.writeFloat(value.volume)
            output.writeFloat(value.pitch)
            output.writeLong(value.seed)
        }
    }
}