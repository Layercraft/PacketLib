package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Set Action Bar Text | 0x42 | play | clientbound
 *
 * @param text text
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18043#Set_Action_Bar_Text">https://wiki.vg/Protocol#Set_Action_Bar_Text</a>
 */

@MinecraftPacket(id = 0x42, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class ActionBarPacket(
    val text: String,
) : ClientBoundPacket {
    companion object : PacketSerializer<ActionBarPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): ActionBarPacket {
            val text = input.readString()

            return ActionBarPacket(text)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: ActionBarPacket) {
            output.writeString(value.text)
        }
    }
}