package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.NBT
/**
 * Entity Effect | 0x69 | play | clientbound
 *
 * @property entityId entityId
 * @property effectId effectId
 * @property amplifier amplifier
 * @property duration duration
 * @property hideParticles hideParticles
 * @property hasFactorCodec factorCodec is present
 * @property factorCodec factorCodec
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Entity_Effect">https://wiki.vg/Protocol#Entity_Effect</a>
 */

@MinecraftPacket(id = 0x69, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class EntityEffectPacket(
    val entityId: Int, // varint
    val effectId: Int, // varint
    val amplifier: Byte,
    val duration: Int, // varint
    val hideParticles: Byte,
    val hasFactorCodec: Boolean,
    val factorCodec: NBT?,
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityEffectPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): EntityEffectPacket {
            val entityId = input.readVarInt()
            val effectId = input.readVarInt()
            val amplifier = input.readByte()
            val duration = input.readVarInt()
            val hideParticles = input.readByte()
            val hasFactorCodec = input.readBoolean()
            val factorCodec = if (hasFactorCodec) input.readNbt() else null

            return EntityEffectPacket(entityId, effectId, amplifier, duration, hideParticles, hasFactorCodec, factorCodec)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityEffectPacket) {
            output.writeVarInt(value.entityId)
            output.writeVarInt(value.effectId)
            output.writeByte(value.amplifier)
            output.writeVarInt(value.duration)
            output.writeByte(value.hideParticles)
            output.writeBoolean(value.hasFactorCodec)
            if (value.hasFactorCodec) output.writeNbt(value.factorCodec!!)
        }
    }
}
