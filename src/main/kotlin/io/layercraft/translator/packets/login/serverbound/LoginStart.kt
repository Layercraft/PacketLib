package io.layercraft.translator.packets.login.serverbound

import io.layercraft.translator.packets.*
import io.layercraft.translator.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.translator.serialization.MinecraftProtocolSerializeInterface
import java.util.UUID

/**
 * Login start | 0x00 | login | server-bound
 *
 * @property name Player's Username.
 * @property hasSigData Whether the next 3 fields should be sent.
 * @property timestamp When the key data will expire. Optional. Only sent if Has Sig Data is true.
 * @property publicKey The encoded bytes of the public key the client received from Mojang. Optional. Only sent if Has Sig Data is true.
 * @property signature The bytes of the public key signature the client received from Mojang. Optional. Only sent if Has Sig Data is true.
 * @property hasPlayerUUID Whether the next field should be sent.
 * @property playerUUID The UUID of the player logging in. Optional. Only sent if Has Player UUID is true.
 * @see <a href="https://wiki.vg/Protocol#Login_Start">https://wiki.vg/Protocol#Login_Start</a>
 */
@MinecraftPacket(packetId = 0x00, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
data class LoginStart(
    val name: String, //string (16)
    val timestamp: Long?, //optional when hasSigData is true
    val publicKey: ByteArray?, //optional when hasSigData is true
    val signature: ByteArray?, //optional when hasSigData is true
    val playerUUID: UUID? //optional when hasPlayerUUID is true
) : ServerBoundPacket {

    val hasSigData: Boolean
        get() = timestamp != null && publicKey != null && signature != null

    val hasPlayerUUID: Boolean
        get() = playerUUID != null
    companion object: PacketSerializer<LoginStart> {

        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): LoginStart {
            val name = input.readString(16)
            val hasSigData = input.readBoolean()
            val timestamp = if (hasSigData) input.readLong() else null
            val publicKey = if (hasSigData) input.readVarIntByteArray() else null
            val signature = if (hasSigData) input.readVarIntByteArray() else null
            val hasPlayerUUID = input.readBoolean()
            val playerUUID = if (hasPlayerUUID) input.readUUID() else null

            return LoginStart(name, timestamp, publicKey, signature, playerUUID)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: LoginStart) {
            output.writeString(value.name, 16)
            val hasSigData = value.timestamp != null && value.publicKey != null && value.signature != null
            output.writeBoolean(hasSigData)
            if (hasSigData) {
                output.writeLong(value.timestamp!!)
                output.writeVarIntByteArray(value.publicKey!!)
                output.writeVarIntByteArray(value.signature!!)
            }
            val hasPlayerUUID = value.playerUUID != null
            output.writeBoolean(hasPlayerUUID)
            if (hasPlayerUUID) output.writeUUID(value.playerUUID!!)
        }
    }
}
