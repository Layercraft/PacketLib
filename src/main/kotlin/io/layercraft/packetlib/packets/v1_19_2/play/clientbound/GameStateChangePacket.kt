package io.layercraft.packetlib.packets.v1_19_2.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Game Event | 0x1d | play | clientbound
 *
 * @property reason reason
 * @property gameMode gameMode
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Game_Event">https://wiki.vg/Protocol#Game_Event</a>
 */

@MinecraftPacket(id = 0x1d, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
data class GameStateChangePacket(
    val reason: UByte,
    val gameMode: Float,
) : ClientBoundPacket {
    companion object : PacketSerializer<GameStateChangePacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): GameStateChangePacket {
            val reason = input.readUByte()
            val gameMode = input.readFloat()

            return GameStateChangePacket(reason, gameMode)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: GameStateChangePacket) {
            output.writeUByte(value.reason)
            output.writeFloat(value.gameMode)
        }
    }
}