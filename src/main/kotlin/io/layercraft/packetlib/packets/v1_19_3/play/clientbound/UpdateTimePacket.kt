package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MCProtocolDeserializer
import io.layercraft.packetlib.serialization.MCProtocolSerializer

/**
 * Update Time | 0x5a | play | clientbound
 *
 * @param age age
 * @param time time
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Time">https://wiki.vg/Protocol#Update_Time</a>
 */

data class UpdateTimePacket(
    val age: Long,
    val time: Long,
) : ClientBoundPacket {
    companion object : PacketSerializer<UpdateTimePacket> {
        override fun deserialize(input: MCProtocolDeserializer<*>): UpdateTimePacket {
            val age = input.readLong()
            val time = input.readLong()

            return UpdateTimePacket(age, time)
        }

        override fun serialize(output: MCProtocolSerializer<*>, value: UpdateTimePacket) {
            output.writeLong(value.age)
            output.writeLong(value.time)
        }
    }
}