package io.layercraft.packetlib.packets.v1_19.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID

/**
 * Use Item | 0x2f | play | serverbound
 *
 * @property target target
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(packetId = 0x2f, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
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