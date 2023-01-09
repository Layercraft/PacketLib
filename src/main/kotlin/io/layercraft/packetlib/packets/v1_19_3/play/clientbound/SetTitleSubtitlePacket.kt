package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Subtitle Text | 0x59 | play | clientbound
 *
 * @param text text
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Set_Subtitle_Text">https://wiki.vg/Protocol#Set_Subtitle_Text</a>
 */

@MinecraftPacket(id = 0x59, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SetTitleSubtitlePacket(
    val text: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<SetTitleSubtitlePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SetTitleSubtitlePacket {
            val text = input.readString()

            return SetTitleSubtitlePacket(text)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SetTitleSubtitlePacket) {
            output.writeString(value.text)
        }
    }
}