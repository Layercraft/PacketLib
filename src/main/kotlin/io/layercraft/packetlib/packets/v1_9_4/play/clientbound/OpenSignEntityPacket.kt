package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position 

/**
 * Encryption Response | 0x2a | play | clientbound
 *
 * @property location location
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x2a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class OpenSignEntityPacket(
    val location: Position,
) : ClientBoundPacket {

    companion object : PacketSerializer<OpenSignEntityPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): OpenSignEntityPacket {
            val location = input.readPosition()

            return OpenSignEntityPacket(location)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: OpenSignEntityPacket) {
            output.writePosition(value.location)
        }
    }
}
