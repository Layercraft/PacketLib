package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID

/**
 * Encryption Response | 0x1b | play | serverbound
 *
 * @property target target
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x1b, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class SpectatePacket(
    val target: UUID,
) : ServerBoundPacket {

    companion object : PacketSerializer<SpectatePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SpectatePacket {
            val target = input.readUUID()

            return SpectatePacket(target)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SpectatePacket) {
            output.writeUUID(value.target)
        }
    }
}
