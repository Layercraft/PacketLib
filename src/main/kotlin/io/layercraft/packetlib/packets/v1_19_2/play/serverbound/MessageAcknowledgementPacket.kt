package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Message Acknowledgment | 0x03 | play | serverbound
 *

 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Message_Acknowledgment">https://wiki.vg/Protocol#Message_Acknowledgment</a>
 */

@MinecraftPacket(id = 0x03, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
class MessageAcknowledgementPacket() : ServerBoundPacket {

    companion object : PacketSerializer<MessageAcknowledgementPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): MessageAcknowledgementPacket {
            return MessageAcknowledgementPacket()
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: MessageAcknowledgementPacket) {
        }
    }
}