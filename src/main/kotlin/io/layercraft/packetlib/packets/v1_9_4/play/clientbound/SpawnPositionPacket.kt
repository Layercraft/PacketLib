package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position

/**
 * Spawn Position | 0x43 | play | clientbound
 *
 * @property location location
 * @see <a href="https://wiki.vg/Protocol#Spawn_Position">https://wiki.vg/Protocol#Spawn_Position</a>
 */

@MinecraftPacket(id = 0x43, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SpawnPositionPacket(
    val location: Position,
) : ClientBoundPacket {

    companion object : PacketSerializer<SpawnPositionPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SpawnPositionPacket {
            val location = input.readPosition()

            return SpawnPositionPacket(location)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SpawnPositionPacket) {
            output.writePosition(value.location)
        }
    }
}
