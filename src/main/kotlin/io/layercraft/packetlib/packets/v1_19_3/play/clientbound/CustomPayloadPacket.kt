package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Plugin Message | 0x15 | play | clientbound
 *
 * @param channel channel
 * @param data data
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Plugin_Message">https://wiki.vg/Protocol#Plugin_Message</a>
 */

data class CustomPayloadPacket(
    val channel: String,
    val data: ByteArray,
) : ClientBoundPacket {
    companion object : PacketSerializer<CustomPayloadPacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): CustomPayloadPacket {
            val channel = input.readString()
            val data = input.readRemainingByteArray()

            return CustomPayloadPacket(channel, data)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: CustomPayloadPacket) {
            output.writeString(value.channel)
            output.writeRemainingByteArray(value.data)
        }
    }
}