package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID

/**
 * Message Header | 0x32 | play | clientbound
 *
 * @property senderUuid senderUuid
 * @see <a href="https://wiki.vg/Protocol#Message_Header">https://wiki.vg/Protocol#Message_Header</a>
 */

@MinecraftPacket(packetId = 0x32, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class MessageHeaderPacket(
    val senderUuid: UUID,
) : ClientBoundPacket {

    companion object : PacketSerializer<MessageHeaderPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): MessageHeaderPacket {
            val senderUuid = input.readUUID()

            return MessageHeaderPacket(senderUuid)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: MessageHeaderPacket) {
            output.writeUUID(value.senderUuid)
        }
    }
}