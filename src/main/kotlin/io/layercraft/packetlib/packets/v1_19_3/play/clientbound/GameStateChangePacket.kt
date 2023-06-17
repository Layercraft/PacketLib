package io.layercraft.packetlib.packets.v1_19_3.play.clientbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Game Event | 0x1c | play | clientbound
 *
 * @param reason reason
 * @param gameMode gameMode
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=18067#Game_Event">https://wiki.vg/Protocol#Game_Event</a>
 */

@MinecraftPacket(id = 0x1c, state = PacketState.PLAY, direction = PacketDirection.CLIENTBOUND)
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