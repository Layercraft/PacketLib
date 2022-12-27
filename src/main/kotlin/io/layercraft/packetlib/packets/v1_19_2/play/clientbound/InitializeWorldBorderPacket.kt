package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x1f | play | clientbound
 *
 * @property x x
 * @property z z
 * @property oldDiameter oldDiameter
 * @property newDiameter newDiameter
 * @property speed speed
 * @property portalTeleportBoundary portalTeleportBoundary
 * @property warningBlocks warningBlocks
 * @property warningTime warningTime
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x1f, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class InitializeWorldBorderPacket(
    val x: Double,
    val z: Double,
    val oldDiameter: Double,
    val newDiameter: Double,
    val speed: Int, // varint
    val portalTeleportBoundary: Int, // varint
    val warningBlocks: Int, // varint
    val warningTime: Int, // varint
) : ClientBoundPacket {

    companion object : PacketSerializer<InitializeWorldBorderPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): InitializeWorldBorderPacket {
            val x = input.readDouble()
            val z = input.readDouble()
            val oldDiameter = input.readDouble()
            val newDiameter = input.readDouble()
            val speed = input.readVarInt()
            val portalTeleportBoundary = input.readVarInt()
            val warningBlocks = input.readVarInt()
            val warningTime = input.readVarInt()

            return InitializeWorldBorderPacket(x, z, oldDiameter, newDiameter, speed, portalTeleportBoundary, warningBlocks, warningTime)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: InitializeWorldBorderPacket) {
            output.writeDouble(value.x)
            output.writeDouble(value.z)
            output.writeDouble(value.oldDiameter)
            output.writeDouble(value.newDiameter)
            output.writeVarInt(value.speed)
            output.writeVarInt(value.portalTeleportBoundary)
            output.writeVarInt(value.warningBlocks)
            output.writeVarInt(value.warningTime)
        }
    }
}