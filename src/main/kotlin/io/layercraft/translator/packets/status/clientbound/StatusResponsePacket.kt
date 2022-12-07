package io.layercraft.translator.packets.status.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface

/**
 * Status response | 0x00 | status | client-bound
 *
 * @property jsonResponse String (32767) - See Server List Ping#Response; as with all strings this is prefixed by its length as a VarInt.
 * @see <a href="https://wiki.vg/Protocol#Status_Response">https://wiki.vg/Protocol#Status_Response</a>
 */
@MinecraftPacket(packetId = 0x00, state = PacketState.STATUS, direction = PacketDirection.CLIENTBOUND)
data class StatusResponsePacket(
    val jsonResponse: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<StatusResponsePacket> {

        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): StatusResponsePacket {
            val jsonResponse = input.readString(32767)

            return StatusResponsePacket(jsonResponse)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: StatusResponsePacket) {
            output.writeString(value.jsonResponse, 32767)
        }
    }
}