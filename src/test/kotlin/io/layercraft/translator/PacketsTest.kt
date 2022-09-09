package io.layercraft.translator

import io.layercraft.translator.packets.PacketType
import io.layercraft.translator.packets.Packets
import org.junit.jupiter.api.Test

internal class PacketsTest{


    @Test
    fun `test all packets in enum`(){
        val packets: Array<PacketType<*>> = Packets.getAll()
        for (packet in packets){
            println(packet)

            val klass = packet.kclass
        }
    }
}
