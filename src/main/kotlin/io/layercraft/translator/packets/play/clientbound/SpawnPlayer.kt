package io.layercraft.translator.packets.play.clientbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc
import java.util.*

/**
 * Spawn player | 0x02 | play | clientbound
 *
 * This packet is sent by the server when a player comes into visible range, not when a player joins.
 * @property entityId VarInt - A unique integer ID mostly used in the protocol to identify the player.
 * @property playerUUID UUID - See below for notes on offline mode and NPCs.
 * @property x
 * @property y
 * @property z
 * @property yaw
 * @property pitch
 * @see <a href="https://wiki.vg/Protocol#Spawn_Player">https://wiki.vg/Protocol#Spawn_Player</a>
 */
@MinecraftPacket(0x02, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class SpawnPlayer(
    val entityId: Int,
    val playerUUID: UUID,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float,
) : ClientBoundPacket {
    companion object : PacketSerializer<SpawnPlayer> {
        override fun serialize(input: Input): SpawnPlayer {
            Int
            val entityId = input.mc.readVarInt()
            val playerUUID = input.mc.readUUID()
            val x = input.mc.readDouble()
            val y = input.mc.readDouble()
            val z = input.mc.readDouble()
            val yaw = input.mc.readAngle()
            val pitch = input.mc.readAngle()

            return SpawnPlayer(entityId, playerUUID, x, y, z, yaw, pitch)
        }

        override fun deserialize(output: Output, value: SpawnPlayer) {
            output.mc.writeVarInt(value.entityId)
            output.mc.writeUUID(value.playerUUID)
            output.mc.writeDouble(value.x)
            output.mc.writeDouble(value.y)
            output.mc.writeDouble(value.z)
            output.mc.writeAngle(value.yaw)
            output.mc.writeAngle(value.pitch)
        }

    }
}
