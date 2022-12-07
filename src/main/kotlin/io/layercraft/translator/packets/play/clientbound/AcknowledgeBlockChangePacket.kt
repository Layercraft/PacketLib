package io.layercraft.translator.packets.play.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Acknowledge block change | 0x05 | play | client-bound
 *
 * @property sequenceId Represents the sequence to acknowledge, this is used for properly syncing block changes to the client after interactions.
 * @see <a href="https://wiki.vg/Protocol#Acknowledge_Block_Change">https://wiki.vg/Protocol#Acknowledge_Block_Change</a>
 */
@MinecraftPacket(0x05, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class AcknowledgeBlockChangePacket(
    val sequenceId: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<AcknowledgeBlockChangePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): AcknowledgeBlockChangePacket {
            val sequenceId = input.readVarInt()

            return AcknowledgeBlockChangePacket(sequenceId)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: AcknowledgeBlockChangePacket) {
            output.writeVarInt(value.sequenceId)
        }
    }
}