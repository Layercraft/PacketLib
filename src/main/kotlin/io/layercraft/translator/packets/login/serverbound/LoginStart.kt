package io.layercraft.translator.packets.login.serverbound

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc
import java.util.*

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

        override fun serialize(input: Input): LoginStart {
            val name = input.mc.readString(16)
            val hasSigData = input.mc.readBoolean()
            val timestamp = if (hasSigData) input.mc.readLong() else null
            val publicKey = if (hasSigData) input.mc.readVarIntByteArray() else null
            val signature = if (hasSigData) input.mc.readVarIntByteArray() else null
            val hasPlayerUUID = input.mc.readBoolean()
            val playerUUID = if (hasPlayerUUID) input.mc.readUUID() else null

            return LoginStart(name, timestamp, publicKey, signature, playerUUID)
        }

        override fun deserialize(output: Output, value: LoginStart) {
            output.mc.writeString(value.name, 16)
            val hasSigData = value.timestamp != null && value.publicKey != null && value.signature != null
            output.mc.writeBoolean(hasSigData)
            if (hasSigData) {
                output.mc.writeLong(value.timestamp!!)
                output.mc.writeVarIntByteArray(value.publicKey!!)
                output.mc.writeVarIntByteArray(value.signature!!)
            }
            val hasPlayerUUID = value.playerUUID != null
            output.mc.writeBoolean(hasPlayerUUID)
            if (hasPlayerUUID) output.mc.writeUUID(value.playerUUID!!)
        }
    }
}
