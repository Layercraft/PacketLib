package io.layercraft.translator.packets.play.clientbound

import io.layercraft.translator.data.entity.EntityAnimationType
import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Entity Animation (clientbound) | 0x03 | play | client-bound
 *
 * Sent whenever an entity should change animation.
 * @property entityId Player ID.
 * @property animation Animation ID (see [EntityAnimationType]).
 * @see <a href="https://wiki.vg/Protocol#Entity_Animation_.28clientbound.29">Entity Animation (clientbound)</a>
 */
@MinecraftPacket(0x03, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class EntityAnimation(
    val entityId: Int, // varint
    val animation: EntityAnimationType, // unsigned byte
) : ClientBoundPacket {
    companion object : PacketSerializer<EntityAnimation> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EntityAnimation {
            val entityId = input.readVarInt()
            val animation = EntityAnimationType.values()[input.readUByte().toInt()]

            return EntityAnimation(entityId, animation)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EntityAnimation) {
            output.writeVarInt(value.entityId)
            output.writeUByte(value.animation.id.toUByte())
        }
    }
}