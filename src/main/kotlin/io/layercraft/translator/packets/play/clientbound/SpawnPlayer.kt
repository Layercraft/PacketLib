package io.layercraft.translator.packets.play.clientbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID

/**
 * Spawn player | 0x02 | play | clientbound
 *
 * This packet is sent by the server when a player comes into visible range, not when a player joins.
 * @property entityId A unique integer ID mostly used in the protocol to identify the player.
 * @property playerUUID See below for notes on offline mode and NPCs.
 * @property x
 * @property y
 * @property z
 * @property yaw
 * @property pitch
 * @see <a href="https://wiki.vg/Protocol#Spawn_Player">https://wiki.vg/Protocol#Spawn_Player</a>
 */
@MinecraftPacket(0x02, PacketState.PLAY, PacketDirection.CLIENTBOUND)
data class SpawnPlayer(
    val entityId: Int, // varint
    val playerUUID: UUID,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float
) : ClientBoundPacket {
    companion object : PacketSerializer<SpawnPlayer> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): SpawnPlayer {
            val entityId = input.readVarInt()
            val playerUUID = input.readUUID()
            val x = input.readDouble()
            val y = input.readDouble()
            val z = input.readDouble()
            val yaw = input.readAngle()
            val pitch = input.readAngle()

            return SpawnPlayer(entityId, playerUUID, x, y, z, yaw, pitch)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: SpawnPlayer) {
            output.writeVarInt(value.entityId)
            output.writeUUID(value.playerUUID)
            output.writeDouble(value.x)
            output.writeDouble(value.y)
            output.writeDouble(value.z)
            output.writeAngle(value.yaw)
            output.writeAngle(value.pitch)
        }
    }
}