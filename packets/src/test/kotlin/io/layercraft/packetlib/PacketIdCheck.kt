package io.layercraft.packetlib

import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PacketIdCheck {

    /*@ParameterizedTest
    @MethodSource("generator")
    fun `check if all packets have the right name`(codec: Codec) {
        checkCodec(codec)
    }

    private fun generator() = emptyArray<MinecraftCodecs>() //arrayOf(MinecraftCodecs.V1_19_3, MinecraftCodecs.V1_19_4)

    private fun checkCodec(codec: Codec) {
        codec.packets.forEach { (state, registry) ->
            registry.clientPacketMap.forEach { (_, packet) ->
                val qualifiedName = packet.packet.qualifiedName!!
                assertTrue { qualifiedName.contains(state.name, ignoreCase = true) }
            }

            registry.serverPacketMap.forEach { (_, packet) ->
                val qualifiedName = packet.packet.qualifiedName!!
                assertTrue { qualifiedName.contains(state.name, ignoreCase = true) }
            }
        }
    }*/
}