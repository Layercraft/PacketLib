package io.layercraft.packetlib.packets.v1_19_2.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Rename Item | 0x23 | play | serverbound
 *
 * @property name name
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Rename_Item">https://wiki.vg/Protocol#Rename_Item</a>
 */

@MinecraftPacket(id = 0x23, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class NameItemPacket(
    val name: String,
) : ServerBoundPacket {

    companion object : PacketSerializer<NameItemPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): NameItemPacket {
            val name = input.readString()

            return NameItemPacket(name)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: NameItemPacket) {
            output.writeString(value.name)
        }
    }
}