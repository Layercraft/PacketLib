package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import io.layercraft.packetlib.types.Position
/**
 * Set Default Spawn Position | 0x4c | play | clientbound
 *
 * @param location location
 * @param angle angle
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Default_Spawn_Position">https://wiki.vg/Protocol#Set_Default_Spawn_Position</a>
 */

@MinecraftPacket(id = 0x4c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SpawnPositionPacket(
    val location: Position,
    val angle: Float,
) : ClientBoundPacket {
    companion object : PacketSerializer<SpawnPositionPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SpawnPositionPacket {
            val location = input.readPosition()
            val angle = input.readFloat()

            return SpawnPositionPacket(location, angle)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SpawnPositionPacket) {
            output.writePosition(value.location)
            output.writeFloat(value.angle)
        }
    }
}