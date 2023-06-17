package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Border Size | 0x45 | play | clientbound
 *
 * @param diameter diameter
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Set_Border_Size">https://wiki.vg/Protocol#Set_Border_Size</a>
 */

@MinecraftPacket(id = 0x45, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class WorldBorderSizePacket(
    val diameter: Double,
) : ClientBoundPacket {
    companion object : PacketSerializer<WorldBorderSizePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): WorldBorderSizePacket {
            val diameter = input.readDouble()

            return WorldBorderSizePacket(diameter)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: WorldBorderSizePacket) {
            output.writeDouble(value.diameter)
        }
    }
}