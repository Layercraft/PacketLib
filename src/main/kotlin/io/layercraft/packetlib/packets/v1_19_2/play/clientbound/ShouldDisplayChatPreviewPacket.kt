package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x4e | play | clientbound
 *
 * @property shouldDisplayChatPreview should_display_chat_preview
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x4e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ShouldDisplayChatPreviewPacket(
    val shouldDisplayChatPreview: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<ShouldDisplayChatPreviewPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ShouldDisplayChatPreviewPacket {
            val shouldDisplayChatPreview = input.readBoolean()

            return ShouldDisplayChatPreviewPacket(shouldDisplayChatPreview)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ShouldDisplayChatPreviewPacket) {
            output.writeBoolean(value.shouldDisplayChatPreview)
        }
    }
}