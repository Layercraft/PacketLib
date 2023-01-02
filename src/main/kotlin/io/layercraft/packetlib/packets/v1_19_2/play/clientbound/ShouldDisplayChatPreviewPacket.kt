package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Display Chat Preview | 0x4e | play | clientbound
 *
 * @param shouldDisplayChatPreview should_display_chat_preview
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Set_Display_Chat_Preview">https://wiki.vg/Protocol#Set_Display_Chat_Preview</a>
 */

@MinecraftPacket(id = 0x4e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ShouldDisplayChatPreviewPacket(
    val shouldDisplayChatPreview: Boolean,
) : ClientBoundPacket {
    companion object : PacketSerializer<ShouldDisplayChatPreviewPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ShouldDisplayChatPreviewPacket {
            val shouldDisplayChatPreview = input.readBoolean()

            return ShouldDisplayChatPreviewPacket(shouldDisplayChatPreview)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ShouldDisplayChatPreviewPacket) {
            output.writeBoolean(value.shouldDisplayChatPreview)
        }
    }
}