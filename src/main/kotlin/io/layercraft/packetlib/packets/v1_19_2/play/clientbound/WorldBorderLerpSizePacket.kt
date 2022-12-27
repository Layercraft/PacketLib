package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Border Lerp Size | 0x45 | play | clientbound
 *
 * @property oldDiameter oldDiameter
 * @property newDiameter newDiameter
 * @property speed speed
 * @see <a href="https://wiki.vg/Protocol#Set_Border_Lerp_Size">https://wiki.vg/Protocol#Set_Border_Lerp_Size</a>
 */

@MinecraftPacket(id = 0x45, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class WorldBorderLerpSizePacket(
    val oldDiameter: Double,
    val newDiameter: Double,
    val speed: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<WorldBorderLerpSizePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): WorldBorderLerpSizePacket {
            val oldDiameter = input.readDouble()
            val newDiameter = input.readDouble()
            val speed = input.readVarInt()

            return WorldBorderLerpSizePacket(oldDiameter, newDiameter, speed)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: WorldBorderLerpSizePacket) {
            output.writeDouble(value.oldDiameter)
            output.writeDouble(value.newDiameter)
            output.writeVarInt(value.speed)
        }
    }
}