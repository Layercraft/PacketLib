package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Time Update | 0x44 | play | clientbound
 *
 * @property age age
 * @property time time
 * @see <a href="https://wiki.vg/Protocol#Time_Update">https://wiki.vg/Protocol#Time_Update</a>
 */

@MinecraftPacket(id = 0x44, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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
