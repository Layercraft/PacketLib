package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x4b | play | clientbound
 *
 * @property entityId entityId
 * @property effectId effectId
 * @property amplifier amplifier
 * @property duration duration
 * @property hideParticles hideParticles
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x4b, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityEffectPacket(
    val entityId: Int, // varint
    val effectId: Byte,
    val amplifier: Byte,
    val duration: Int, // varint
    val hideParticles: Byte,
) : ClientBoundPacket {

    companion object : PacketSerializer<EntityEffectPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityEffectPacket {
            val entityId = input.readVarInt()
            val effectId = input.readByte()
            val amplifier = input.readByte()
            val duration = input.readVarInt()
            val hideParticles = input.readByte()

            return EntityEffectPacket(entityId, effectId, amplifier, duration, hideParticles)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityEffectPacket) {
            output.writeVarInt(value.entityId)
            output.writeByte(value.effectId)
            output.writeByte(value.amplifier)
            output.writeVarInt(value.duration)
            output.writeByte(value.hideParticles)
        }
    }
}
