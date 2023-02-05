package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Border Lerp Size | 0x44 | play | clientbound
 *
 * @param oldDiameter oldDiameter
 * @param newDiameter newDiameter
 * @param speed speed
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17964#Set_Border_Lerp_Size">https://wiki.vg/Protocol#Set_Border_Lerp_Size</a>
 */

@MinecraftPacket(id = 0x44, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class WorldBorderLerpSizePacket(
    val oldDiameter: Double,
    val newDiameter: Double,
    val speed: Int, // varint
) : ClientBoundPacket {
    companion object : PacketSerializer<WorldBorderLerpSizePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): WorldBorderLerpSizePacket {
            val oldDiameter = input.readDouble()
            val newDiameter = input.readDouble()
            val speed = input.readVarInt()

            return WorldBorderLerpSizePacket(oldDiameter, newDiameter, speed)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: WorldBorderLerpSizePacket) {
            output.writeDouble(value.oldDiameter)
            output.writeDouble(value.newDiameter)
            output.writeVarInt(value.speed)
        }
    }
}