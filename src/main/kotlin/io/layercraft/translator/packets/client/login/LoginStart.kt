package io.layercraft.translator.packets.client.login

import io.ktor.utils.io.core.*
import io.layercraft.translator.packets.*
import io.layercraft.translator.utils.mc
import java.util.*

/**
 * Login start | 0x00 | login | server-bound
 *
 * @property name String (16) - Player's Username.
 * @property hasSigData Boolean - Whether the next 3 fields should be sent.
 * @property timestamp Long - When the key data will expire. Optional. Only sent if Has Sig Data is true.
 * @property publicKey VarInt ByteArray - The encoded bytes of the public key the client received from Mojang. Optional. Only sent if Has Sig Data is true.
 * @property signature VarInt ByteArray - The bytes of the public key signature the client received from Mojang. Optional. Only sent if Has Sig Data is true.
 * @property hasPlayerUUID Boolean - Whether the next field should be sent.
 * @property playerUUID UUID - The UUID of the player logging in. Optional. Only sent if Has Player UUID is true.
 * @see <a href="https://wiki.vg/Protocol#Login_Start">https://wiki.vg/Protocol#Login_Start</a>
 */
@MinecraftPacket(packetId = 0x00, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
data class LoginStart(
    val name: String,
    val hasSigData: Boolean,
    val timestamp: Long?,
    val publicKey: ByteArray?,
    val signature: ByteArray?,
    val hasPlayerUUID: Boolean,
    val playerUUID: UUID?
) : ClientPacket {
    companion object: PacketSerializer<LoginStart> {

        override fun serialize(input: Input): LoginStart {
            val name = input.mc.readString(16)
            val hasSigData = input.mc.readBoolean()
            val timestamp = if (hasSigData) input.mc.readLong() else null
            val publicKey = if (hasSigData) input.mc.readVarIntByteArray() else null
            val signature = if (hasSigData) input.mc.readVarIntByteArray() else null
            val hasPlayerUUID = input.mc.readBoolean()
            val playerUUID = if (hasPlayerUUID) input.mc.readUUID() else null

            return LoginStart(name, hasSigData, timestamp, publicKey, signature, hasPlayerUUID, playerUUID)
        }

        override fun deserialize(output: Output, value: LoginStart) {
            output.mc.writeString(value.name, 16)
            output.mc.writeBoolean(value.hasSigData)
            if (value.hasSigData) {
                output.mc.writeLong(value.timestamp!!)
                output.mc.writeVarIntByteArray(value.publicKey!!)
                output.mc.writeVarIntByteArray(value.signature!!)
            }
            if (value.hasPlayerUUID) output.mc.writeUUID(value.playerUUID!!)
        }
    }
}
