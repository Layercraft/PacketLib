package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Border Size | 0x43 | play | clientbound
 *
 * @property diameter diameter
 * @see <a href="https://wiki.vg/Protocol#Set_Border_Size">https://wiki.vg/Protocol#Set_Border_Size</a>
 */

@MinecraftPacket(packetId = 0x43, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class WorldBorderSizePacket(
    val diameter: Double,
) : ClientBoundPacket {

    companion object : PacketSerializer<WorldBorderSizePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): WorldBorderSizePacket {
            val diameter = input.readDouble()

            return WorldBorderSizePacket(diameter)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: WorldBorderSizePacket) {
            output.writeDouble(value.diameter)
        }
    }
}