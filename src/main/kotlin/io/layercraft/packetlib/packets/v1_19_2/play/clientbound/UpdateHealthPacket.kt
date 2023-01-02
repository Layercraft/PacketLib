package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Health | 0x55 | play | clientbound
 *
 * @param health health
 * @param food food
 * @param foodSaturation foodSaturation
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Set_Health">https://wiki.vg/Protocol#Set_Health</a>
 */

@MinecraftPacket(id = 0x55, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class UpdateHealthPacket(
    val health: Float,
    val food: Int, // varint
    val foodSaturation: Float,
) : ClientBoundPacket {
    companion object : PacketSerializer<UpdateHealthPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateHealthPacket {
            val health = input.readFloat()
            val food = input.readVarInt()
            val foodSaturation = input.readFloat()

            return UpdateHealthPacket(health, food, foodSaturation)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateHealthPacket) {
            output.writeFloat(value.health)
            output.writeVarInt(value.food)
            output.writeFloat(value.foodSaturation)
        }
    }
}