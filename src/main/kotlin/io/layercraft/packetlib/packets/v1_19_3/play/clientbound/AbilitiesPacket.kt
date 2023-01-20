package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Player Abilities | 0x30 | play | clientbound
 *
 * @param flags flags
 * @param flyingSpeed flyingSpeed
 * @param walkingSpeed walkingSpeed
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17935#Player_Abilities">https://wiki.vg/Protocol#Player_Abilities</a>
 */

@MinecraftPacket(id = 0x30, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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