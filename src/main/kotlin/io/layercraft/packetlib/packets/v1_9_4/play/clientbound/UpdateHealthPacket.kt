package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x3e | play | clientbound
 *
 * @property health health
 * @property food food
 * @property foodSaturation foodSaturation
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x3e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class UpdateHealthPacket(
    val health: Float,
    val food: Int, // varint
    val foodSaturation: Float,
) : ClientBoundPacket {

    companion object : PacketSerializer<UpdateHealthPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateHealthPacket {
            val health = input.readFloat()
            val food = input.readVarInt()
            val foodSaturation = input.readFloat()

            return UpdateHealthPacket(health, food, foodSaturation)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateHealthPacket) {
            output.writeFloat(value.health)
            output.writeVarInt(value.food)
            output.writeFloat(value.foodSaturation)
        }
    }
}
