package io.layercraft.packetlib

import io.layercraft.packetlib.codec.MinecraftCodec
import io.layercraft.packetlib.packets.MinecraftPacket
import io.layercraft.packetlib.packets.PacketDirection
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue
import io.layercraft.packetlib.codec.MinecraftCodecs as MC

class PacketIdCheck {

    @Test
    fun `check if all ids are same in registry and in packet class`() {
        val codecs = listOf(MC.V1_9_4, MC.V1_19, MC.V1_19_2)
        codecs.forEach { checkCodec(it) }
    }

    private fun checkCodec(codec: MinecraftCodec){
        codec.packets.forEach { (state, registry) ->
            registry.clientPacketMap.forEach { (id, packet) ->
                val annotation = packet.packet.annotations.filterIsInstance<MinecraftPacket>().first()

                assert(annotation.id == id) {
                    "Packet ${packet.packet.simpleName} has id ${annotation.id} in annotation, but $id in registry"
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

                assert(annotation.id == id) {
                    "Packet ${packet.packet.simpleName} has id ${annotation.id} in annotation, but $id in registry"
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