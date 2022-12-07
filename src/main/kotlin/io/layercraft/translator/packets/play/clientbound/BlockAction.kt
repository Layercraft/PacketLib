package io.layercraft.translator.packets.play.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.translator.types.Position

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
    val blockType: Int,
) : ClientBoundPacket {

    companion object : PacketSerializer<BlockAction> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): BlockAction {
            val location = input.readPosition()
            val actionId = input.readUByte()
            val actionParameter = input.readUByte()
            val blockType = input.readVarInt()

            return BlockAction(location, actionId, actionParameter, blockType)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: BlockAction) {
            output.writePosition(value.location)
            output.writeUByte(value.actionId)
            output.writeUByte(value.actionParameter)
            output.writeVarInt(value.blockType)
        }

    }

}