package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x5b | play | clientbound
 *
 * @property text text
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x5b, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SetTitleSubtitlePacket(
    val text: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<SetTitleSubtitlePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SetTitleSubtitlePacket {
            val text = input.readString()

            return SetTitleSubtitlePacket(text)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SetTitleSubtitlePacket) {
            output.writeString(value.text)
        }
    }
}