package io.layercraft.packetlib.codec

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MinecraftCodecsTest {

    @Test
    fun `is copy working`() {
        MinecraftCodecs.V1_19_4.packets.forEach {
            Assertions.assertIterableEquals(it.value.serverPacketMap.values, MinecraftCodecs.V1_19_3.packets[it.key]?.serverPacketMap?.values)
            Assertions.assertIterableEquals(it.value.clientPacketMap.values, MinecraftCodecs.V1_19_3.packets[it.key]?.clientPacketMap?.values)
        }
    }
}