package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Look At | 0x37 | play | clientbound
 *
 * @param feetEyes feet_eyes
 * @param x x
 * @param y y
 * @param z z
 * @param isEntity isEntity
 * @param entityId entityId
 * @param entityFeetEyes entity_feet_eyes
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17964#Look_At">https://wiki.vg/Protocol#Look_At</a>
 */

@MinecraftPacket(id = 0x37, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class FacePlayerPacket(
    val feetEyes: Int, // varint
    val x: Double,
    val y: Double,
    val z: Double,
    val isEntity: Boolean,
    val entityId: Int?, // varint
    val entityFeetEyes: String?,
) : ClientBoundPacket {
    companion object : PacketSerializer<FacePlayerPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): FacePlayerPacket {
            val feetEyes = input.readVarInt()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val isEntity = input.readBoolean()
            val entityId = when (isEntity) {
                true -> input.readVarInt()
                else -> null
            }
            val entityFeetEyes = when (isEntity) {
                true -> input.readString()
                else -> null
            }

            return FacePlayerPacket(feetEyes, x, y, z, isEntity, entityId, entityFeetEyes)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: FacePlayerPacket) {
            output.writeVarInt(value.feetEyes)
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeBoolean(value.isEntity)
            when (value.isEntity) {
                true -> output.writeVarInt(value.entityId!!)
                else -> {}
            }
            when (value.isEntity) {
                true -> output.writeString(value.entityFeetEyes!!)
                else -> {}
            }
        }
    }
}