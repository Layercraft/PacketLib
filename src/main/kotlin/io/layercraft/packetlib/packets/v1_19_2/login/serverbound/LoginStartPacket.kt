package io.layercraft.packetlib.packets.v1_19_2.login.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID
/**
 * Login Start | 0x00 | login | serverbound
 *
 * @property username username
 * @property hasSignature signature is present
 * @property timestamp timestamp
 * @property publicKey publicKey
 * @property signature signature
 * @property hasPlayerUUID playerUUID is present
 * @property playerUUID playerUUID
 * @see <a href="https://wiki.vg/index.php?title=Protocol&oldid=17873#Login_Start">https://wiki.vg/Protocol#Login_Start</a>
 */

@MinecraftPacket(id = 0x00, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
data class LoginStartPacket(
    val username: String,
    val hasSignature: Boolean,
    val timestamp: Long?,
    val publicKey: ByteArray?,
    val signature: ByteArray?,
    val hasPlayerUUID: Boolean,
    val playerUUID: UUID?,
) : ServerBoundPacket {
    companion object : PacketSerializer<LoginStartPacket> {
        override fun deserialize(input: MinecraftProtocolDeserializeInterface<*>): LoginStartPacket {
            val username = input.readString()
            val hasSignature = input.readBoolean()
            val timestamp = if (hasSignature) input.readLong() else null
            val publicKey = if (hasSignature) input.readVarIntByteArray() else null
            val signature = if (hasSignature) input.readVarIntByteArray() else null
            val hasPlayerUUID = input.readBoolean()
            val playerUUID = if (hasPlayerUUID) input.readUUID() else null

            return LoginStartPacket(username, hasSignature, timestamp, publicKey, signature, hasPlayerUUID, playerUUID)
        }

        override fun serialize(output: MinecraftProtocolSerializeInterface<*>, value: LoginStartPacket) {
            output.writeString(value.username)
            output.writeBoolean(value.hasSignature)
            if (value.hasSignature) output.writeLong(value.timestamp!!)
            if (value.hasSignature) output.writeVarIntByteArray(value.publicKey!!)
            if (value.hasSignature) output.writeVarIntByteArray(value.signature!!)
            output.writeBoolean(value.hasPlayerUUID)
            if (value.hasPlayerUUID) output.writeUUID(value.playerUUID!!)
        }
    }
}
