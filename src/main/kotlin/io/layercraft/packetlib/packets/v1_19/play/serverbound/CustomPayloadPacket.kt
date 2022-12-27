package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x0c | play | serverbound
 *
 * @property channel channel
 * @property data data
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x0c, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class CustomPayloadPacket(
    val channel: String,
    val data: ByteArray,
) : ServerBoundPacket {

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