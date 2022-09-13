package io.layercraft.translator.packets.play.clientbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc

/**
 * Acknowledge block change | 0x05 | play | client-bound
 *
 * @property sequenceId Represents the sequence to acknowledge, this is used for properly syncing block changes to the client after interactions.
 * @constructor Create empty Acknowledge block change
 */
@MinecraftPacket(0x05, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class AcknowledgeBlockChange(
    val sequenceId: Int
): ClientBoundPacket {
    companion object: PacketSerializer<AcknowledgeBlockChange> {
        override fun serialize(input: Input): AcknowledgeBlockChange {
            val sequenceId = input.mc.readVarInt()

            return AcknowledgeBlockChange(sequenceId)
        }

        override fun deserialize(output: Output, value: AcknowledgeBlockChange) {
            output.mc.writeVarInt(value.sequenceId)
        }
    }
}
