package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 *  | 0x31 | play | clientbound
 *
 * @property flags flags
 * @property flyingSpeed flyingSpeed
 * @property walkingSpeed walkingSpeed
 * @see <a href="https://wiki.vg/Protocol#Player_Abilities_.28clientbound.29">https://wiki.vg/Protocol#Player_Abilities_.28clientbound.29</a>
 */

@MinecraftPacket(packetId = 0x31, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class AbilitiesPacket(
    val flags: Byte,
    val flyingSpeed: Float,
    val walkingSpeed: Float,
) : ClientBoundPacket {

    companion object : PacketSerializer<AbilitiesPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): AbilitiesPacket {
            val flags = input.readByte()
            val flyingSpeed = input.readFloat()
            val walkingSpeed = input.readFloat()

            return AbilitiesPacket(flags, flyingSpeed, walkingSpeed)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: AbilitiesPacket) {
            output.writeByte(value.flags)
            output.writeFloat(value.flyingSpeed)
            output.writeFloat(value.walkingSpeed)
        }
    }
}