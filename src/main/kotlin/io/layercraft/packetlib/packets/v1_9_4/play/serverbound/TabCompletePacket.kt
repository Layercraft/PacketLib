package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 *  | 0x01 | play | serverbound
 *
 * @property text text
 * @property assumeCommand assumeCommand
 * @see <a href="https://wiki.vg/Protocol#Tab-Complete_.28serverbound.29">https://wiki.vg/Protocol#Tab-Complete_.28serverbound.29</a>
 */

@MinecraftPacket(packetId = 0x01, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class TabCompletePacket(
    val text: String,
    val assumeCommand: Boolean,
) : ServerBoundPacket {

    companion object : PacketSerializer<TabCompletePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): TabCompletePacket {
            val text = input.readString()
            val assumeCommand = input.readBoolean()

            return TabCompletePacket(text, assumeCommand)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: TabCompletePacket) {
            output.writeString(value.text)
            output.writeBoolean(value.assumeCommand)
        }
    }
}
