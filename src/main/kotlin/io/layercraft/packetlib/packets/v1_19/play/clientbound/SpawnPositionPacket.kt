package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position

/**
 * Use Item | 0x4a | play | clientbound
 *
 * @property location location
 * @property angle angle
 * @see <a href="https://wiki.vg/Protocol#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x4a, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SpawnPositionPacket(
    val location: Position,
    val angle: Float,
) : ClientBoundPacket {

    companion object : PacketSerializer<SpawnPositionPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SpawnPositionPacket {
            val location = input.readPosition()
            val angle = input.readFloat()

            return SpawnPositionPacket(location, angle)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SpawnPositionPacket) {
            output.writePosition(value.location)
            output.writeFloat(value.angle)
        }
    }
}