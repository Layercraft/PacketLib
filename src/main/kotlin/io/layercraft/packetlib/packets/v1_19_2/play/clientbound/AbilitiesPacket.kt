package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Player Abilities (clientbound) | 0x31 | play | clientbound
 *
 * @property flags flags
 * @property flyingSpeed flyingSpeed
 * @property walkingSpeed walkingSpeed
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Player_Abilities_.28clientbound.29">https://wiki.vg/Protocol#Player_Abilities_.28clientbound.29</a>
 */

@MinecraftPacket(id = 0x31, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class AbilitiesPacket(
    val flags: Byte,
    val flyingSpeed: Float,
    val walkingSpeed: Float,
) : ClientBoundPacket {
    companion object : PacketSerializer<AbilitiesPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): AbilitiesPacket {
            val flags = input.readByte()
            val flyingSpeed = input.readFloat()
            val walkingSpeed = input.readFloat()

            return AbilitiesPacket(flags, flyingSpeed, walkingSpeed)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: AbilitiesPacket) {
            output.writeByte(value.flags)
            output.writeFloat(value.flyingSpeed)
            output.writeFloat(value.walkingSpeed)
        }
    }
}