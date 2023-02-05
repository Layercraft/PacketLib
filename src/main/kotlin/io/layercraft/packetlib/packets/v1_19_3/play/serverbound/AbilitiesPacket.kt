package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Player Abilities | 0x1b | play | serverbound
 *
 * @param flags flags
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17964#Player_Abilities_2">https://wiki.vg/Protocol#Player_Abilities_2</a>
 */

@MinecraftPacket(id = 0x1b, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class AbilitiesPacket(
    val flags: Byte,
) : ServerBoundPacket {
    companion object : PacketSerializer<AbilitiesPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): AbilitiesPacket {
            val flags = input.readByte()

            return AbilitiesPacket(flags)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: AbilitiesPacket) {
            output.writeByte(value.flags)
        }
    }
}