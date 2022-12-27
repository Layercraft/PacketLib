package io.layercraft.packetlib.packets.v1_9_4.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface


/**
 * Encryption Response | 0x1e | play | clientbound
 *
 * @property reason reason
 * @property gameMode gameMode
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(packetId = 0x1e, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class GameStateChangePacket(
    val reason: UByte,
    val gameMode: Float,
) : ClientBoundPacket {

    companion object : PacketSerializer<GameStateChangePacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): GameStateChangePacket {
            val reason = input.readUByte()
            val gameMode = input.readFloat()

            return GameStateChangePacket(reason, gameMode)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: GameStateChangePacket) {
            output.writeUByte(value.reason)
            output.writeFloat(value.gameMode)
        }
    }
}
