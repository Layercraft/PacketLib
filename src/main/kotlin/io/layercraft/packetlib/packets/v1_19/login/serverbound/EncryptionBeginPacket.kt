package io.layercraft.packetlib.packets.v1_19.login.serverbound

import io.layercraft.packetlib.packets.*
import io.layercraft.packetlib.serialization.MinecraftProtocolDeserializeInterface
import io.layercraft.packetlib.serialization.MinecraftProtocolSerializeInterface

/**
 * Encryption Response | 0x01 | login | serverbound
 *
 * @property hasVerifyToken hasVerifyToken
 * @see <a href="https://wiki.vg/Protocol#Encryption_Response">https://wiki.vg/Protocol#Encryption_Response</a>
 */

@MinecraftPacket(id = 0x01, state = PacketState.LOGIN, direction = PacketDirection.SERVERBOUND)
data class EncryptionBeginPacket(
    val hasVerifyToken: Boolean,
) : ServerBoundPacket {

    companion object : PacketSerializer<EncryptionBeginPacket> {
        override fun serialize(input: MinecraftProtocolDeserializeInterface<*>): EncryptionBeginPacket {
            val hasVerifyToken = input.readBoolean()

            return EncryptionBeginPacket(hasVerifyToken)
        }

        override fun deserialize(output: MinecraftProtocolSerializeInterface<*>, value: EncryptionBeginPacket) {
            output.writeBoolean(value.hasVerifyToken)
        }
    }
}