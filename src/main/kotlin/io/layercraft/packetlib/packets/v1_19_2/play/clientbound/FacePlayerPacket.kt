package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Look At | 0x38 | play | clientbound
 *
 * @property feet_eyes feet_eyes
 * @property x x
 * @property y y
 * @property z z
 * @property isEntity isEntity
 * @see <a href="https://wiki.vg/Protocol#Look_At">https://wiki.vg/Protocol#Look_At</a>
 */

@MinecraftPacket(packetId = 0x38, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class FacePlayerPacket(
    val feet_eyes: Int, // varint
    val x: Double,
    val y: Double,
    val z: Double,
    val isEntity: Boolean,
) : ClientBoundPacket {

    companion object : PacketSerializer<FacePlayerPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): FacePlayerPacket {
            val feet_eyes = input.readVarInt()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val isEntity = input.readBoolean()

            return FacePlayerPacket(feet_eyes, x, y, z, isEntity)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: FacePlayerPacket) {
            output.writeVarInt(value.feet_eyes)
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeBoolean(value.isEntity)
        }
    }
}