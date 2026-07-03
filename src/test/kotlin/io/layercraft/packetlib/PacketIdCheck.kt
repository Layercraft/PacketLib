package io.layercraft.packetlib

import io.layercraft.packetlib.codec.Codec
import io.layercraft.packetlib.codec.MinecraftCodecs
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PacketIdCheck {

    @ParameterizedTest
    @MethodSource("generator")
    fun `check if all packets have the right name`(codec: Codec) {
        checkCodec(codec)
    }

    private fun generator() = MinecraftCodecs.ALL.values.toTypedArray()

    private fun checkCodec(codec: Codec) {
        codec.packets.forEach { (state, registry) ->
            registry.clientBoundPackets.forEach { (_, packet) ->
                val qualifiedName = packet.packet.qualifiedName!!
                assertTrue { qualifiedName.contains(state.name, ignoreCase = true) }
                assertTrue { qualifiedName.contains("clientbound") }
            }

            registry.serverBoundPackets.forEach { (_, packet) ->
                val qualifiedName = packet.packet.qualifiedName!!
                assertTrue { qualifiedName.contains(state.name, ignoreCase = true) }
                assertTrue { qualifiedName.contains("serverbound") }
            }
        }
    }
}
