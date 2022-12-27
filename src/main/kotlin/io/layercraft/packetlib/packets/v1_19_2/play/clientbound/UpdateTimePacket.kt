package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Use Item | 0x5c | play | clientbound
 *
 * @property age age
 * @property time time
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Use_Item">https://wiki.vg/Protocol#Use_Item</a>
 */

@MinecraftPacket(id = 0x5c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class UpdateTimePacket(
    val age: Long,
    val time: Long,
) : ClientBoundPacket {

    companion object : PacketSerializer<UpdateTimePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): UpdateTimePacket {
            val age = input.readLong()
            val time = input.readLong()

            return UpdateTimePacket(age, time)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: UpdateTimePacket) {
            output.writeLong(value.age)
            output.writeLong(value.time)
        }
    }
}