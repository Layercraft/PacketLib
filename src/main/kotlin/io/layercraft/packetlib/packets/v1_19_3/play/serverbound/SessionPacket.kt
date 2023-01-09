package io.layercraft.packetlib.packets.v1_19_3.play.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Player Session | 0x20 | play | serverbound
 *
 * @param sessionUUID sessionUUID
 * @param expireTime expireTime
 * @param publicKey publicKey
 * @param signature signature
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17932#Player_Session">https://wiki.vg/Protocol#Player_Session</a>
 */

@MinecraftPacket(id = 0x20, state = PacketState.PLAY, direction = PacketDirection.SERVERBOUND)
data class SessionPacket(
    val sessionUUID: UUID,
    val expireTime: Long,
    val publicKey: ByteArray,
    val signature: ByteArray,
) : ServerBoundPacket {
    companion object : PacketSerializer<SessionPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): SessionPacket {
            val sessionUUID = input.readUUID()
            val expireTime = input.readLong()
            val publicKey = input.readVarIntByteArray()
            val signature = input.readVarIntByteArray()

            return SessionPacket(sessionUUID, expireTime, publicKey, signature)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: SessionPacket) {
            output.writeUUID(value.sessionUUID)
            output.writeLong(value.expireTime)
            output.writeVarIntByteArray(value.publicKey)
            output.writeVarIntByteArray(value.signature)
        }
    }
}