package io.layercraft.translator.packets.play.clientbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.types.Position
import io.layercraft.translator.utils.mc

/**
 * Block action | 0x08 | play | client-bound
 *
 * Sets non-persistent actions and animations for different blocks.
 *
 * @property location
 * @property actionId The type of action for a specific block.
 * @property actionParameter Value for selected action or animation for block.
 * @see <a href="https://wiki.vg/Protocol#Block_Action">https://wiki.vg/Protocol#Block_Action</a>
 */
@MinecraftPacket(0x08, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class BlockAction(
    val location: Position,
    val actionId: UByte,
    val actionParameter: UByte,
    val blockType: Int
) : ClientBoundPacket {

    companion object : PacketSerializer<BlockAction> {
        override fun serialize(input: Input): BlockAction {
            val location = input.mc.readPosition()
            val actionId = input.mc.readUByte()
            val actionParameter = input.mc.readUByte()
            val blockType = input.mc.readVarInt()

            return BlockAction(location, actionId, actionParameter, blockType)
        }

        override fun deserialize(output: Output, value: BlockAction) {
            output.mc.writePosition(value.location)
            output.mc.writeUByte(value.actionId)
            output.mc.writeUByte(value.actionParameter)
            output.mc.writeVarInt(value.blockType)
        }

    }

}