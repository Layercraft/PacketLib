package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 *  | 0x16 | play | clientbound
 *
 * @property channel channel
 * @property data data
 * @see <a href="https://wiki.vg/Protocol#Plugin_Message_.28clientbound.29">https://wiki.vg/Protocol#Plugin_Message_.28clientbound.29</a>
 */

@MinecraftPacket(packetId = 0x16, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class CustomPayloadPacket(
    val channel: String,
    val data: ByteArray,
) : ClientBoundPacket {

    companion object : PacketSerializer<CustomPayloadPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): CustomPayloadPacket {
            val channel = input.readString()
            val data = input.readRemainingByteArray()

            return CustomPayloadPacket(channel, data)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: CustomPayloadPacket) {
            output.writeString(value.channel)
            output.writeRemainingByteArray(value.data)
        }
    }
}