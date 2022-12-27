package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Spawn Global Entity | 0x02 | play | clientbound
 *
 * @property entityId entityId
 * @property type type
 * @property x x
 * @property y y
 * @property z z
 * @see <a href="https://wiki.vg/Protocol#Spawn_Global_Entity">https://wiki.vg/Protocol#Spawn_Global_Entity</a>
 */

@MinecraftPacket(id = 0x02, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SpawnEntityWeatherPacket(
    val entityId: Int, // varint
    val type: Byte,
    val x: Double,
    val y: Double,
    val z: Double,
) : ClientBoundPacket {

    companion object : PacketSerializer<SpawnEntityWeatherPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SpawnEntityWeatherPacket {
            val entityId = input.readVarInt()
            val type = input.readByte()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()

            return SpawnEntityWeatherPacket(entityId, type, x, y, z)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SpawnEntityWeatherPacket) {
            output.writeVarInt(value.entityId)
            output.writeByte(value.type)
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
        }
    }
}
