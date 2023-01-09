package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Title Animation Times | 0x5c | play | clientbound
 *
 * @param fadeIn fadeIn
 * @param stay stay
 * @param fadeOut fadeOut
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Set_Title_Animation_Times">https://wiki.vg/Protocol#Set_Title_Animation_Times</a>
 */

@MinecraftPacket(id = 0x5c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class SetTitleTimePacket(
    val fadeIn: Int,
    val stay: Int,
    val fadeOut: Int,
) : ClientBoundPacket {
    companion object : PacketSerializer<SetTitleTimePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SetTitleTimePacket {
            val fadeIn = input.readInt()
            val stay = input.readInt()
            val fadeOut = input.readInt()

            return SetTitleTimePacket(fadeIn, stay, fadeOut)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SetTitleTimePacket) {
            output.writeInt(value.fadeIn)
            output.writeInt(value.stay)
            output.writeInt(value.fadeOut)
        }
    }
}