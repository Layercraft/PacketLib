package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Seen Advancements | 0x25 | play | serverbound
 *
 * @property action action
 * @property tabId tabId
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Seen_Advancements">https://wiki.vg/Protocol#Seen_Advancements</a>
 */

@MinecraftPacket(id = 0x25, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class AdvancementTabPacket(
    val action: Int, // varint
    val tabId: String?,
) : ServerBoundPacket {
    companion object : PacketSerializer<AdvancementTabPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): AdvancementTabPacket {
            val action = input.readVarInt()
            val tabId = when (action) {
                0 -> input.readString()
                1 -> null
                else -> null
            }

            return AdvancementTabPacket(action, tabId)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: AdvancementTabPacket) {
            output.writeVarInt(value.action)
            when (value.action) {
                0 -> output.writeString(value.tabId!!)
                else -> {}
            }
        }
    }
}