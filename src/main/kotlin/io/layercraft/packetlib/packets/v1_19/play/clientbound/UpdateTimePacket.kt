package io.layercraft.packetlib.packets.v1_19.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Update Time | 0x59 | play | clientbound
 *
 * @property age age
 * @property time time
 * @see <a href="https://wiki.vg/Protocol#Update_Time">https://wiki.vg/Protocol#Update_Time</a>
 */

@MinecraftPacket(id = 0x59, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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