package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Teleport To Entity | 0x30 | play | serverbound
 *
 * @property target target
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Teleport_To_Entity">https://wiki.vg/Protocol#Teleport_To_Entity</a>
 */

@MinecraftPacket(id = 0x30, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class SpectatePacket(
    val target: UUID,
) : ServerBoundPacket {
    companion object : PacketSerializer<SpectatePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SpectatePacket {
            val target = input.readUUID()

            return SpectatePacket(target)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SpectatePacket) {
            output.writeUUID(value.target)
        }
    }
}
