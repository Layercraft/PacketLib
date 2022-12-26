package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Action Bar Text | 0x40 | play | clientbound
 *
 * @property text text
 * @see <a href="https://wiki.vg/Protocol#Set_Action_Bar_Text">https://wiki.vg/Protocol#Set_Action_Bar_Text</a>
 */

@MinecraftPacket(packetId = 0x40, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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