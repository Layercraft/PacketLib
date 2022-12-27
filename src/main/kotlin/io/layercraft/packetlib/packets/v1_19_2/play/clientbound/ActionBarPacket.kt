package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Action Bar Text | 0x43 | play | clientbound
 *
 * @property text text
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Set_Action_Bar_Text">https://wiki.vg/Protocol#Set_Action_Bar_Text</a>
 */

@MinecraftPacket(id = 0x43, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ActionBarPacket(
    val text: String,
) : ClientBoundPacket {

    companion object : PacketSerializer<ActionBarPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): ActionBarPacket {
            val text = input.readString()

            return ActionBarPacket(text)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: ActionBarPacket) {
            output.writeString(value.text)
        }
    }
}