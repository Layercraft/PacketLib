package io.layercraft.packetlib.codec

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MinecraftCodecsTest {

    @Test
    fun `every protocol version has a codec`() {
        ProtocolVersion.entries.forEach { version ->
            val codec = MinecraftCodecs.fromProtocolVersion(version)
            Assertions.assertEquals(version, codec.protocolVersion)
            Assertions.assertTrue(codec.packets.isNotEmpty(), "$version has no packet registrations")
        }
    }

    @Test
    fun `versions sharing protocol data are copies`() {
        // 1.20.1 shares the pc/1.20 protocol definition with 1.20.
        MinecraftCodecs.V1_20_1.packets.forEach { (state, registry) ->
            Assertions.assertIterableEquals(
                registry.clientBoundPackets.values,
                MinecraftCodecs.V1_20.packets[state]?.clientBoundPackets?.values,
            )
            Assertions.assertIterableEquals(
                registry.serverBoundPackets.values,
                MinecraftCodecs.V1_20.packets[state]?.serverBoundPackets?.values,
            )
        }
    }

    @Test
    fun `packet registrations are consistent`() {
        MinecraftCodecs.ALL.values.forEach { codec ->
            codec.packets.forEach { (_, registry) ->
                registry.clientBoundPackets.forEach { (id, packet) -> Assertions.assertEquals(id, packet.packetId) }
                registry.serverBoundPackets.forEach { (id, packet) -> Assertions.assertEquals(id, packet.packetId) }
            }
        }
    }
}
