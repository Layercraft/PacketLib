package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Plugin Message | 0x0c | play | serverbound
 *
 * @param channel channel
 * @param data data
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Plugin_Message_2">https://wiki.vg/Protocol#Plugin_Message_2</a>
 */

data class CustomPayloadPacket(
    val channel: String,
    val data: ByteArray,
) : ServerBoundPacket {
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