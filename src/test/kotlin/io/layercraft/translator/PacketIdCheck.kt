package io.layercraft.translator

import io.layercraft.translator.codec.MinecraftCodecs
import io.layercraft.translator.packets.MinecraftPacket
import io.layercraft.translator.packets.PacketDirection
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class PacketIdCheck {

    @Test
    fun `check if all ids are same in registry and in packet class`() {
        val codec = MinecraftCodecs.V_1_19_2

        codec.packets.forEach { (state, registry) ->
            registry.clientPacketMap.forEach { (id, packet) ->
                val annotation = packet.packet.annotations.filterIsInstance<MinecraftPacket>().first()

                assert(annotation.packetId == id) {
                    "Packet ${packet.packet.simpleName} has id ${annotation.packetId} in annotation, but $id in registry"
                }

                assert(annotation.state == state) {
                    "Packet ${packet.packet.simpleName} has state ${annotation.state} in annotation, but $state in registry"
                }

                assert(annotation.direction == PacketDirection.SERVERBOUND) {
                    "Packet ${packet.packet.simpleName} has direction ${annotation.direction} in annotation, but ${PacketDirection.CLIENTBOUND} in registry"
                }

                val qualifiedName = packet.packet.qualifiedName!!
                assertTrue { qualifiedName.contains(state.name, ignoreCase = true) }
            }

            registry.serverPacketMap.forEach { (id, packet) ->
                val annotation = packet.packet.annotations.filterIsInstance<MinecraftPacket>().first()

                assert(annotation.packetId == id) {
                    "Packet ${packet.packet.simpleName} has id ${annotation.packetId} in annotation, but $id in registry"
                }

                assert(annotation.state == state) {
                    "Packet ${packet.packet.simpleName} has state ${annotation.state} in annotation, but $state in registry"
                }

                assert(annotation.direction == PacketDirection.CLIENTBOUND) {
                    "Packet ${packet.packet.simpleName} has direction ${annotation.direction} in annotation, but ${PacketDirection.SERVERBOUND} in registry"
                }

                val qualifiedName = packet.packet.qualifiedName!!
                assertTrue { qualifiedName.contains(state.name, ignoreCase = true) }
            }
        }
    }
}