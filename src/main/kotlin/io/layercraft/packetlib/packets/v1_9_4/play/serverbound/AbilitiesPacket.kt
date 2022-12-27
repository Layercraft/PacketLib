package io.layercraft.packetlib.packets.v1_9_4.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 *  | 0x12 | play | serverbound
 *
 * @property flags flags
 * @property flyingSpeed flyingSpeed
 * @property walkingSpeed walkingSpeed
 * @see <a href="https://wiki.vg/Protocol#Player_Abilities_.28serverbound.29">https://wiki.vg/Protocol#Player_Abilities_.28serverbound.29</a>
 */

@MinecraftPacket(packetId = 0x12, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class AbilitiesPacket(
    val flags: Byte,
    val flyingSpeed: Float,
    val walkingSpeed: Float,
) : ServerBoundPacket {

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
